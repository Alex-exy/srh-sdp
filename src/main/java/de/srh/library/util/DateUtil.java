package de.srh.library.util;

import java.util.Date;

public class DateUtil {

    public static long daysToToday(Date date) {
        return (new Date().getTime() - date.getTime()) / (1000 * 60 * 60 * 24);
    }
}

