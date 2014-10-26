package net.dg.domain.movie;

import lombok.Getter;
import net.dg.generic.money.Money;

@Getter
public class Movie {
    private final String title;
    private final int runningTime;
    private final Money amount;

    public Movie(String title, int runningTime, Money amount) {
        this.title = title;
        this.runningTime = runningTime;
        this.amount = amount;
    }
}
