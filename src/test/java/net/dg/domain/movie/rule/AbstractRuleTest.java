package net.dg.domain.movie.rule;

import net.dg.domain.movie.Movie;
import net.dg.domain.movie.Showing;
import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.money.Money;

public abstract class AbstractRuleTest {

    protected Showing createShowing(int showingSequence, DateTimeInterval playingInterval) {
        Movie movie = new Movie("title", 120, Money.wons(10000));
        return new Showing(movie, showingSequence, playingInterval);
    }
}
