package net.dg.generic.base;

import java.util.Date;

public class DateTimeInterval {
    private Date startDateTime;
    private Date endDateTime;

    public DateTimeInterval(Date startDateTime, Date endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }
}
