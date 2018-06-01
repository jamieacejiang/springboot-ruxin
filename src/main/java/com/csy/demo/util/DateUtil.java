package com.csy.demo.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title: 日期工具类
 * @description: 包括获取系统当前时间等
 * @author: jiangman
 * @time: 2018/05/29 13:20
 */
public class DateUtil {
    /**
    * @Description: 获取当前系统时间 格式:yyyy-MM-dd HH:mm:ss
    * @author: jiangman
    * @date: 2018/5/29 16:00
    */
    public static String getNowDateStr(){
        Date date = new Date();
        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
