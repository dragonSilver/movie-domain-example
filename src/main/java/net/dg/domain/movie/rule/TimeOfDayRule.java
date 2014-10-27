package net.dg.domain.movie.rule;

import lombok.NoArgsConstructor;
import net.dg.domain.movie.Showing;
import net.dg.generic.base.DayOfWeek;
import net.dg.generic.base.TimeInterval;

import javax.persistence.*;

@Entity
@DiscriminatorValue("T")
@NoArgsConstructor
public class TimeOfDayRule extends Rule{

    @Column
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @Embedded
    private TimeInterval interval;

    public TimeOfDayRule(DayOfWeek day, TimeInterval interval) {
        this.day = day;
        this.interval = interval;
    }

    @Override
    public boolean isStatisfiedBy(Showing showing) {
        boolean resultPlayingOn = showing.playingOn(day);
        boolean resultInterval = interval.includes(showing.getPlayingInterval());
        return resultPlayingOn && resultInterval;
    }
}
