package com.luckypeng.roc.mock.func;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author chenzhipeng
 * @date 2018/7/28 10:55
 */
@Data
@AllArgsConstructor
public class MockFunction {
    /**
     * 函数名
     */
    private String name;

    /**
     * 函数描述
     */
    private String desc;

    /**
     * 调用样式
     */
    private String layout;

    /**
     * 执行方法
     */
    private Method method;
}
