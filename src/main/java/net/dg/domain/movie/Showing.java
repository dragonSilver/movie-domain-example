package net.dg.domain.movie;

import lombok.Getter;
import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.base.DayOfWeek;
import net.dg.generic.money.Money;

@Getter
public class Showing {
    private final Movie movie;
    private int sequence;
    private DateTimeInterval playingInterval;


    public Showing(Movie movie, int showingSequence, DateTimeInterval playingInterval) {
        this.movie = movie;
        this.sequence = showingSequence;
        this.playingInterval = playingInterval;
    }

    public Money getFixedFee() {
        return movie.getFee();
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public boolean playingOn(DayOfWeek day) {
        return playingInterval.getStart().getDayOfWeek() == day.getCode();
    }


    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, audienceCount);
    }

    public Money calculateFee() {
        return movie.calculateFee(this);
    }
}
