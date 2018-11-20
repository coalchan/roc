package com.luckypeng.roc.common.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author chenzhipeng
 */
public final class EmptyUtils {

    private EmptyUtils() {

    }

    /**
     * 判断集合是否为空 coll->null->true coll-> coll.size() == 0 -> true
     *
     * @param coll
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * 判断集合是否不为空
     *
     * @param coll
     * @return
     */
    public static <T> boolean isNotEmpty(Collection<T> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断map是否不为空
     *
     * @param map
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个对象是否为空
     *
     * @param obj
     * @return
     */
    public static <T> boolean isEmpty(T obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof Optional) {
            return !((Optional) obj).isPresent();
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        // else
        return false;
    }

    /**
     * 判断数组是否不为空
     */
    public static <T> boolean isNotEmpty(T[] datas) {
        return !isEmpty(datas);
    }

    /**
     * 判断数组是否不为空
     */
    public static <T> boolean isEmpty(T[] datas) {
        return (datas == null || datas.length == 0);
    }


    /**
     * 判断一个对象是否不为空
     *
     * @param t
     * @return
     */
    public static <T> boolean isNotEmpty(T t) {
        return !isEmpty(t);
    }

    /**
     * 判断多个T是否存在空对象，只判断null不判断空 可用于多参数简化代码： 如： if (parameter1==null || parameter2==null || parameter3==null) 可以简化为： if
     * (EmptyUtils.hasNull(parameter1, parameter2,parameter3))
     *
     * @param datas
     * @return
     * @author wangweizhen
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean hasNull(T... datas) {
        for (T t : datas) {
            if (t == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个Map是否存在空对象
     *
     * @param datas
     * @param <K>
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <K, V> boolean hasEmpty(Map<K, V>... datas) {
        for (Map<K, V> data : datas) {
            if (isEmpty(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个Collection是否存在空对象
     *
     * @param datas
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean hasEmpty(Collection<T>... datas) {
        for (Collection<T> data : datas) {
            if (isEmpty(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找到第一个不为null的值
     * @param data
     * @param <T>
     * @return
     */
    public static <T> T ifNull(T... data) {
        for (T d:data) {
            if (d != null) {
                return d;
            }
        }
        return null;
    }
}
