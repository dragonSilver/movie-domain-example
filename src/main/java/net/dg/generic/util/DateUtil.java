package net.dg.generic.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DateUtil {
    
    public static String toDateString(DateTime dateTime) {
        return dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));
    }
}
