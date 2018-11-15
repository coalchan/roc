package com.luckypeng.roc.core.util;

import com.luckypeng.roc.core.exception.RocException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzhipeng
 */
public class AssertionUtils {
    private AssertionUtils() {}

    public static void isTrue(boolean bool, RocException e) {
        if (!bool) {
            throw e;
        }
    }

    public static void isFalse(boolean bool, RocException e) {
        isTrue(!bool, e);
    }

    public static void isEmpty(Object t, RocException e) {
        isTrue(EmptyUtils.isEmpty(t), e);
    }

    public static void isNotEmpty(Object t, RocException e) {
        isFalse(EmptyUtils.isEmpty(t), e);
    }

    public static void notBlank(String s, RocException e) {
        isTrue(StringUtils.isNotBlank(s),  e);
    }
}
