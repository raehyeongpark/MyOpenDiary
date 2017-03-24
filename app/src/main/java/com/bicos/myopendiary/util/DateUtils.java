package com.bicos.myopendiary.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class DateUtils {

    public static String today(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        return format.format(date);
    }

    public static String todayTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA);
        return format.format(date);
    }
}
