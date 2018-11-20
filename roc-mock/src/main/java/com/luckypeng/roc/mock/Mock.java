package com.luckypeng.roc.mock;

import com.luckypeng.roc.mock.func.FunctionHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author chenzhipeng
 * @date 2018/10/10 11:56
 */
@Slf4j
public class Mock {
    public static Object mock(String funcExpression) {
        return FunctionHelper.executeFunc(funcExpression.trim());
    }

    public static Object[] mock(List<String> rule) {
        return rule.stream().map(String::trim).map(FunctionHelper::executeFunc).toArray();
    }
}