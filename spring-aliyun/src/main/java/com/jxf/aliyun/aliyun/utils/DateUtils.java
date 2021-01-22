package com.jxf.aliyun.aliyun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DEFAULT_DATE_FORMAT="yyyy/MM/dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    public static String format(Date date){
        return format(date,DEFAULT_DATE_FORMAT);
    }

    public static String format(Date date,String format){
        return dateFormat.format(date);
    }
}
