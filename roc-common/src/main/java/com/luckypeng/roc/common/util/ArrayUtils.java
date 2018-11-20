package com.luckypeng.roc.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhipeng
 */
public class ArrayUtils {
    private ArrayUtils() {}

    /**
     * 将一个元素复制n份，成为一个数组返回
     * @param obj
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> multiClone(T obj, int size) {
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(obj);
        }
        return list;
    }
}
