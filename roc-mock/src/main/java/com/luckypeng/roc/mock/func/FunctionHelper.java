package com.luckypeng.roc.mock.func;

import com.luckypeng.roc.core.util.AssertionUtils;
import com.luckypeng.roc.core.util.EmptyUtils;
import com.luckypeng.roc.core.exception.RocException;
import com.luckypeng.roc.mock.exception.MockErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenzhipeng
 * @date 2018/7/28 10:38
 */
@Slf4j
public class FunctionHelper {
    private FunctionHelper(){}

    public final static Map<String, MockFunction> CUSTOM_FUNCTION_MAP = load();

    /**
     * 导入函数
     */
    private static Map<String, MockFunction> load() {
        Class<Functions> clazz = Functions.class;
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(FunctionInfo.class))
                .map(method -> {
                        FunctionInfo info = method.getAnnotation(FunctionInfo.class);
                        return new MockFunction(info.name(), info.desc(), info.layout(), method);
                    })
                .collect(Collectors.toMap(x->x.getName(), x->x));
    }

    /**
     * 执行函数
     * @param funcExpression
     * @return
     */
    public static Object executeFunc(String funcExpression) {
        int bracketIndex = funcExpression.indexOf('(');
        String funcName = funcExpression.substring(0, bracketIndex);
        Object[] params = null;
        String paramStr = funcExpression.substring(bracketIndex+1, funcExpression.length()-1);
        if (EmptyUtils.isNotEmpty(paramStr)) {
            String[] paramStrArray = paramStr.split(",");
            params = new Object[paramStrArray.length];
            for (int i = 0; i < paramStrArray.length; i++) {
                paramStrArray[i] = paramStrArray[i].trim();
                if (paramStrArray[i].charAt(0) == '\'') {
                    // 函数参数为字符串
                    params[i] = paramStrArray[i].substring(1, paramStrArray[i].length()-1);
                } else {
                    // 其他
                    params[i] = paramStrArray[i];
                }
            }
        }
        return FunctionHelper.apply(funcName, params);
    }

    /**
     * 执行函数
     * @param functionName
     * @param params
     * @return
     */
    public static Object apply(String functionName, Object... params) {
        AssertionUtils.isTrue(CUSTOM_FUNCTION_MAP.containsKey(functionName),
                new RocException(MockErrorCode.FUNC_RUN_ERROR, functionName + "函数不存在"));
        MockFunction mockFunction = CUSTOM_FUNCTION_MAP.get(functionName);
        if (mockFunction.getMethod().getParameterCount() == 0) {
            return invokeWithCatch(mockFunction, null);
        }
        return invokeWithCatch(mockFunction, new Object[]{params});
    }

    /**
     * 通过反射调用方法
     * @param mockFunction
     * @param params
     * @return
     */
    private static Object invokeWithCatch(MockFunction mockFunction, Object... params) {
        try {
            return mockFunction.getMethod().invoke(null, params);
        } catch (IllegalAccessException|InvocationTargetException e) {
            throw new RocException(MockErrorCode.FUNC_RUN_ERROR, mockFunction.getName() + "|" + Arrays.asList(params).toString());
        }
    }
}
