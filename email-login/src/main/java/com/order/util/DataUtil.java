package com.order.util;

import java.util.Calendar;

/**
 * @author ：clt
 * @Date ：Created in 14:52 2019/8/26
 */
public class DataUtil {


    /**
     * 计算今天还剩多少秒
     */
    public static int  remainingTime(){
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final double seconds = (cal.getTimeInMillis() - System.currentTimeMillis())/1000;
        return (int) seconds;
    }
}
