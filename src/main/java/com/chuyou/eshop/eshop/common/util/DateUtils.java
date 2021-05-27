package com.chuyou.eshop.eshop.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ranter
 * @Date: 2021/4/1 10:31 下午
 * @Description: 日期工具类
 */
public class DateUtils {

    /**
     * 标准格式
     */
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(PATTERN);
        }
    };

    /**
     * 获取当前时间
     * @return 当前时间
     * @throws ParseException
     */
    public static Date getCurrentTime() throws ParseException {
        SimpleDateFormat dateFormat = DateUtils.dateFormat.get();
        return dateFormat.parse(dateFormat.format(new Date()));
    }

    /**
     * 将Date 对象格式化成：yyyy-MM-dd HH:mm:ss
     * @param date Date 对象
     * @return
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = DateUtils.dateFormat.get();
        return dateFormat.format(date);
    }

    /**
     * 将日期字符串转化为Date对象
     * @param dateTime 日期字符串
     * @return Date 日期
     * @throws ParseException
     */
    public static Date parseDateTime(String dateTime) throws ParseException {
        SimpleDateFormat dateFormat = DateUtils.dateFormat.get();
        return dateFormat.parse(dateTime);
    }
}
