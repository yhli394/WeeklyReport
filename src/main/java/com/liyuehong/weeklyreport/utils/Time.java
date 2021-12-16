package com.liyuehong.weeklyreport.utils;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yhli3
 * @classname Time
 * @Date 2021/12/9 19:40
 */
public class Time {

    //public static String getTimeInterval(Date date){
    //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //    Calendar cal = Calendar.getInstance();
    //    cal.setTime(date);
    //    int i = cal.get(Calendar.DAY_OF_WEEK);
    //    if(i==1){
    //        cal.add(Calendar.DAY_OF_WEEK,-1);
    //    }
    //    cal.setFirstDayOfWeek(Calendar.MONDAY);
    //    int day = cal.get(Calendar.DAY_OF_WEEK);
    //    cal.add(Calendar.DATE,cal.getFirstDayOfWeek()-day);
    //    String s = sdf.format(cal.getTime());
    //
    //}
}
