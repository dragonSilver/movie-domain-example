package net.dg.generic.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.dg.generic.util.DateUtil;
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
public class DateTimeInterval extends AbstractInterval{

    @Column(name = "startDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "endDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    private DateTimeInterval(DateTime start, DateTime end) {
        this.start = start.toDate();
        this.end = end.toDate();
    }

    public static DateTimeInterval of(String start, String end) {
        DateTime startDateTime = DateTime.parse(start);
        DateTime endDateTime = DateTime.parse(end);
        return new DateTimeInterval(startDateTime, endDateTime);
    }

    public static DateTimeInterval of(DateTime start, DateTime end) {
        return new DateTimeInterval(start, end);
    }

    @Override
    public boolean includes(AbstractInterval compareInterval) {
        DateTime compareStart = compareInterval.getStartDateTime();
        DateTime compareEnd = compareInterval.getEndDateTime();

        if(getStartDateTime().getMillis() <= compareStart.getMillis()) {
            if(getEndDateTime().getMillis() >= compareEnd.getMillis()) {
                return true;
            }
        }
        return false;
    }

    public Integer getStartDayOfWeek() {
        DateTime dateTime = new DateTime(start);
        return dateTime.getDayOfWeek();
    }

    public String getPlayingTimes() {
        return DateUtil.toDateString(getStartDateTime()) + "~" + DateUtil.toDateString(getEndDateTime());
    }
}
