package com.luckypeng.roc.common.util;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author chenzhipeng
 */
public class DateUtils {
    private static final String DATE_STRING_NOT_NULL = "The 'dateString' must not be null!";
    private static final String DATE_NOT_NULL = "The 'date' must not be null!";

    private DateUtils() {

    }

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    /**
     * 默认日期格式：yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 默认时间格式：yyyyMM
     */
    public static final String YYYYMM_DATE_PATTERN = "yyyyMM";

    /**
     * 默认时间格式：yyyyMMdd
     */
    public static final String YYYYMMDD_DATE_PATTERN = "yyyyMMdd";

    /**
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间戳格式，到毫秒 yyyy-MM-dd HH:mm:ss SSS
     */
    public static final String DEFAULT_DATEDETAIL_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * 1天折算成毫秒数
     */
    public static final long MILLIS_A_DAY = 24 * 3600 * 1000L;

    /**
     * 1小时折算成毫秒数
     */
    public static final long MILLIS_A_HOUR = 3600 * 1000L;

    /**
     * 取得系统当前年份
     *
     * @return
     */
    public static int currentYear() {
        return DateTime.now().getYear();
    }

    /**
     * 取得当前系统日期
     *
     * @return
     */
    public static Date currentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 取得系统当前日期，返回默认日期格式的字符串。
     *
     * @param strFormat
     * @return
     */
    public static String nowDate(String strFormat) {
        return new DateTime().toString(strFormat, Locale.CHINESE);
    }

    /**
     * 取得当前系统时间戳
     *
     * @return
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(new DateTime().getMillis());
    }

    /**
     * 将日期字符串转换为java.util.Date对象
     *
     * @param dateString
     * @param pattern 日期格式
     * @return
     * @throws Exception
     */
    public static Date toDate(String dateString, String pattern) {
        return DateTime.parse(dateString, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 将日期字符串转换为java.util.Date对象
     *
     * @param dateString example:"2017-05-03T15:11:45.7009265+08:00"
     * @return
     * @throws Exception
     */
    public static Date toISODate(String dateString) {
        return DateTime.parse(dateString, ISODateTimeFormat.dateTime()).toDate();
    }

    /**
     * 将日期字符串转换为java.util.Date对象，使用默认日期格式
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static Date toDate(String dateString) {
        return LocalDateTime.parse(dateString).toDate();
    }

    /**
     * 将时间字符串转换为java.util.Date对象
     *
     * @param dateString
     * @return
     * @throws Exception
     */
    public static Date toDateTime(String dateString) {
        return DateTime.parse(dateString, DateTimeFormat.forPattern(DEFAULT_DATETIME_PATTERN)).toDate();
    }

    /**
     * 将java.util.Date对象转换为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String toDateString(Date date, String pattern) {
        return new DateTime(date).toString(pattern, Locale.CHINESE);
    }

    /**
     * 将java.util.Date对象转换为字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
        return new DateTime(date).toString(DEFAULT_DATE_PATTERN, Locale.CHINESE);
    }

    /**
     * 将java.util.Date对象转换为时间字符串，使用默认日期格式
     *
     * @param date
     * @return
     */
    public static String toDateTimeString(Date date) {
        return new DateTime(date).toString(DEFAULT_DATETIME_PATTERN, Locale.CHINESE);
    }

    /**
     * 日期相减
     *
     * @param date
     * @param days
     * @return
     */
    public static Date diffDate(Date date, Integer days) {
        return new DateTime(date).minusDays(days).toDate();

    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     * @author doumingjun create 2007-04-07
     */
    public static long getMillis(Date date) {
        return new DateTime(date).getMillis();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param days 天数
     * @return 返回相加后的日期
     * @author doumingjun create 2007-04-07
     */
    public static Date addDate(Date date, Integer days) {
        return new DateTime(date).plusDays(days).toDate();
    }

    /**
     * 日期增加年数
     *
     * @param date
     * @param years
     * @return
     * @author zhf
     */
    public static Date addYear(Date date, Integer years) {
        return new DateTime(date).plusYears(years).toDate();
    }

    /**
     * 日期增加月数
     *
     * @param date
     * @param months
     * @return
     * @author zhf
     */
    public static Date addMonth(Date date, Integer months) {
        return new DateTime(date).plusMonths(months).toDate();
    }

    /**
     * 日期增加小时
     *
     * @param date
     * @param hours
     * @return
     * @author zhf
     */
    public static Date addHours(Date date, Integer hours) {
        return new DateTime(date).plusHours(hours).toDate();
    }

    /**
     * 日期增加分钟
     *
     * @param date
     * @param minutes
     * @return
     * @author zhf
     */
    public static Date addMinutes(Date date, Integer minutes) {
        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    /**
     * 日期增加秒
     *
     * @param date
     * @param seconds
     * @return
     * @author zhf
     */
    public static Date addSeconds(Date date, Integer seconds) {
        return new DateTime(date).plusSeconds(seconds).toDate();
    }

    private static final int TEN = 10;
    /**
     * 根据季度获得相应的月份
     *
     * @param quarters 季度
     *
     * @return 返回相应的月份
     */
    public static String getMonth(String quarters) {
        String month;
        int m = Integer.parseInt(quarters);
        m = m * 3 - 2;
        if (m > 0 && m < TEN) {
            month = "0" + m;
        } else {
            month = String.valueOf(m);
        }
        return month;
    }

    /**
     * 根据月份获得相应的季度
     *
     * @param month 月份
     *
     * @return 返回相应的季度
     */
    public static String getQuarters(String month) {
        String quarters = null;
        int m = Integer.parseInt(month);
        if (m == JANUARY || m == FEBRUARY || m == MARCH) {
            quarters = "1";
        }
        if (m == APRIL || m == MAY || m == JUNE) {
            quarters = "2";
        }
        if (m == JULY || m == AUGUST || m == SEPTEMBER) {
            quarters = "3";
        }
        if (m == OCTOBER || m == NOVEMBER || m == DECEMBER) {
            quarters = "4";
        }
        return quarters;
    }

    /**
     * 获取日期所在星期的第一天，这里设置第一天为星期日
     *
     * @param datestr
     * @return
     */
    public static String getFirstDateOfWeek(String datestr) {
        DateTime dt = DateTime.parse(datestr);
        return dt.plusDays(-(dt.getDayOfWeek()) + 1).toString(DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取日期所在当年的第几周
     *
     * @param datestr
     * @return
     */
    public static int getWeekOfYear(String datestr) {
        return DateTime.parse(datestr).weekOfWeekyear().get();
    }

    public static Date fromTimeticks(Long ticks) {
        return new DateTime(ticks).toDate();
    }

    public static Long toTimeticks(Date time) {
        return time.getTime();
    }

    /**
     * 获取UTC时间戳（以秒为单位）
     */
    public static int getTimestampInSeconds() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 获取UTC时间戳（以毫秒为单位）
     */
    public static long getTimestampInMillis() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return cal.getTimeInMillis();
    }

}

