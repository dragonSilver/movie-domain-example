package net.dg.generic.base;

import lombok.Getter;
import org.joda.time.DateTime;

@Getter
public class DateTimeInterval {
    private DateTime start;
    private DateTime end;

    private DateTimeInterval(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    public static DateTimeInterval of(String start, String end) {
        DateTime startDateTime = DateTime.parse(start);
        DateTime endDateTime = DateTime.parse(end);
        return new DateTimeInterval(startDateTime, endDateTime);
    }

    public static DateTimeInterval of(DateTime start, DateTime end) {
        return new DateTimeInterval(start, end);
    }

    public boolean includes(DateTimeInterval compareInterval) {
        DateTime compareStart = compareInterval.getStart();
        DateTime compareEnd = compareInterval.getEnd();

        if(start.getMillis() <= compareStart.getMillis()) {
            if(end.getMillis() >= compareEnd.getMillis()) {
                return true;
            }
        }
        return false;
    }
}
