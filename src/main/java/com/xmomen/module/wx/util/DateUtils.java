package com.xmomen.module.wx.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils{

    /**
     * 取当前时间
     *
     * @return 当前时间对象
     */
    public static Date getNowDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 取当前时间
     *
     * @return 当前时间字符串
     */
    public static String getNowDateString(String patten) {
        SimpleDateFormat formatter = new SimpleDateFormat(patten);
        return formatter.format(getNowDate());
    }
}
