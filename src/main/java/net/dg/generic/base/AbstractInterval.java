package net.dg.generic.base;

import org.joda.time.DateTime;

import java.util.Date;

public abstract class AbstractInterval {
    
    public abstract Date getStart();
    public abstract Date getEnd();


    public DateTime getStartDateTime() {
        return new DateTime(getStart());
    }

    public DateTime getEndDateTime() {
        return new DateTime(getEnd());
    }

    public int getStartHourOfDay() {
        return getStartDateTime().getHourOfDay();
    }

    public int getStartMiniteOfHour() {
        return getStartDateTime().getMinuteOfHour();
    }

    public int getStartSecondOfMinite() {
        return getStartDateTime().getSecondOfMinute();
    }

    public int getEndHourOfDay() {
        return getEndDateTime().getHourOfDay();
    }

    public int getEndMiniteOfHour() {
        return getEndDateTime().getMinuteOfHour();
    }

    public int getEndSecondOfMinite() {
        return getEndDateTime().getSecondOfMinute();
    }

    public abstract boolean includes(AbstractInterval interval);
}
