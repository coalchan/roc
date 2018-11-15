package com.luckypeng.roc.mock.func;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenzhipeng
 * @date 2018/7/27 17:52
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionInfo {
    /**
     * 函数名称
     */
    String name();

    /**
     * 函数描述
     */
    String desc();

    /**
     * 调用样式
     * @return
     */
    String layout();
}
