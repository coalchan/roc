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
    /**
     * mock单个
     * @param rule
     * @return
     */
    public static Object mock(String rule) {
        return FunctionHelper.executeFunc(rule.trim());
    }

    /**
     * mock一组规则
     * @param rule
     * @return
     */
    public static Object[] mock(List<String> rule) {
        return rule.stream().map(Mock::mock).toArray();
    }

    /**
     * mock多组规则
     * @param rule
     * @param length
     * @return
     */
    public static Object[][] mock(List<String> rule, int length) {
        Object[][] result = new Object[length][];
        for (int i = 0; i < length; i++) {
            result[i] = mock(rule);
        }
        return result;
    }
}
