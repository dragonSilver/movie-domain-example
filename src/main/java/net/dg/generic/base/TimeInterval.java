package net.dg.generic.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@NoArgsConstructor
@Getter
@ToString
public class TimeInterval extends AbstractInterval{

    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date start;

    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date end;

    private TimeInterval(DateTime start, DateTime end) {
        this.start = start.toDate();
        this.end = end.toDate();
    }

    public static TimeInterval of(String start, String end) {
        DateTime startDateTime = DateTime.parse(start);
        DateTime endDateTime = DateTime.parse(end);
        return new TimeInterval(startDateTime, endDateTime);
    }

    public static TimeInterval of(DateTime start, DateTime end) {
        return new TimeInterval(start, end);
    }


    @Override
    public boolean includes(AbstractInterval compareInterval) {
        boolean resultStart = includeStartTime(compareInterval.getStartDateTime());
        boolean resultEnd = includeEndTime(compareInterval.getEndDateTime());
        return resultStart && resultEnd;
    }

    private boolean includeStartTime(DateTime compareStartTime) {
        if( getStartHourOfDay() > compareStartTime.getHourOfDay()) {
            return false;
        }
        if( getStartHourOfDay() == compareStartTime.getHourOfDay()) {
            if(getStartMiniteOfHour() > compareStartTime.getMinuteOfHour()) {
                return false;
            }
            if(getStartMiniteOfHour() == compareStartTime.getMinuteOfHour()) {
                if(getStartSecondOfMinite() > compareStartTime.getSecondOfMinute()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean includeEndTime(DateTime compareEndTime) {
        if( getEndHourOfDay() < compareEndTime.getHourOfDay()) {
            return false;
        }
        if( getEndHourOfDay() == compareEndTime.getHourOfDay()) {
            if(getEndMiniteOfHour() < compareEndTime.getMinuteOfHour()) {
                return false;
            }
            if(getEndMiniteOfHour() == compareEndTime.getMinuteOfHour()) {
                if(getEndSecondOfMinite() < compareEndTime.getSecondOfMinute()) {
                    return false;
                }
            }
        }

        return true;
    }
}
