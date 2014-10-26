package net.dg.domain.movie.rule;

import net.dg.domain.movie.Showing;
import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.base.DayOfWeek;

public class TimeOfDayRule extends Rule{
    private final DayOfWeek day;
    private final DateTimeInterval interval;

    public TimeOfDayRule(DayOfWeek day, DateTimeInterval interval) {
        this.day = day;
        this.interval = interval;
    }

    @Override
    public boolean isStatisfiedBy(Showing showing) {
        return showing.playingOn(day) && interval.includes(showing.getPlayingInterval());
    }
}
