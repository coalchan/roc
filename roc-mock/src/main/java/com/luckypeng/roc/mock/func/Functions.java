package com.luckypeng.roc.mock.func;

import com.luckypeng.roc.common.util.DateUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 函数集合
 * 方法仅支持无参和可变参数这2种情况
 * @author chenzhipeng
 * @date 2018/7/27 17:46
 */
public class Functions {
    private Functions(){}

    private static Map<String, String> poolMap;
    private static String defaultPool;

    static {
        poolMap = new HashMap<>();
        poolMap.put("lower", "abcdefghijklmnopqrstuvwxyz");
        poolMap.put("upper", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        poolMap.put("number", "0123456789");
        poolMap.put("symbol", "!@#$%^&*()[]");
        defaultPool = poolMap.values().stream().collect(Collectors.joining());
    }

    @FunctionInfo(name = "@today", desc = "当天日期", layout = "@today()")
    public static String today() {
        return DateUtils.nowDate(DateUtils.DEFAULT_DATE_PATTERN);
    }

    @FunctionInfo(name = "@yesterday", desc = "昨天日期", layout = "@yesterday()")
    public static String yesterday() {
        return DateUtils.toDateString(DateUtils.addDate(DateUtils.currentDate(), -1), DateUtils.DEFAULT_DATE_PATTERN);
    }

    @FunctionInfo(name = "@integer", desc = "随机整数", layout = "@integer(min, max)")
    public static long integer(Object... params) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        if (params != null && params.length > 0 && params[0] != null) {
            min = Integer.parseInt(params[0].toString());
        }
        if (params != null && params.length > 1 && params[1] != null) {
            max = Integer.parseInt(params[1].toString());
        }
        return Math.round(Math.random() * (max - min)) + min;
    }

    @FunctionInfo(name = "@float", desc = "随机的浮点数", layout = "@float(min, max, dmin, dmax)")
    public static String ffloat(Object... params) {
        int dmin = 0;
        int dmax = 17;
        if (params != null && params.length >= 3 && params[2] != null) {
            dmin = Integer.parseInt(params[2].toString());
        }
        if (params != null && params.length >= 4 && params[3] != null) {
            dmax = Integer.parseInt(params[3].toString());
        }
        String ret = integer(params) + ".";
        for (int i = 0, dcount = RandomUtils.nextInt(dmin, dmax); i < dcount; i++) {
            // 最后一位不为0
            ret += (i < dcount - 1) ? character("number") : character("123456789");
        }
        return ret;
    }

    @FunctionInfo(name = "@character", desc = "返回一个随机字符", layout = "@character('upper')")
    public static Character character(Object... params) {
        String pool;
        if (params != null && params.length > 0 && params[0] != null) {
            pool = poolMap.get(params[0]);
            if (pool == null) {
                pool = params[0].toString();
            }
        } else {
            pool = defaultPool;
        }
        return pool.charAt(RandomUtils.nextInt(0, pool.length() - 1));
    }

    @FunctionInfo(name = "@string", desc = "返回一个随机字符串", layout = "@string('upper')")
    public static String string(Object... params) {
        String pool = null;
        int len;
        if (params == null) {
            params = new Object[0];
        }
        switch (params.length) {
            case 0:
                len = RandomUtils.nextInt(3, 7);
                break;
            case 1:
                len = Integer.parseInt(params[0].toString());
                break;
            case 2:
                if (params[0] instanceof String) {
                    pool = params[0].toString();
                    len = Integer.parseInt(params[1].toString());
                } else {
                    len = RandomUtils.nextInt(
                            Integer.parseInt(params[0].toString()), Integer.parseInt(params[1].toString()));
                }
                break;
            case 3:
                pool = params[0].toString();
                len = RandomUtils.nextInt(
                        Integer.parseInt(params[1].toString()), Integer.parseInt(params[2].toString()));
                break;
            default:
                len = 0;
                pool = "";
        }
        String text = "";
        for (int i = 0; i < len; i++) {
            text += character(pool);
        }

        return text;
    }

}
