package com.bicos.myopendiary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

public class DateUtils {

    public static String today(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
