package net.dg.domain.movie.discount;

import net.dg.domain.movie.Movie;
import net.dg.domain.movie.Showing;
import net.dg.domain.movie.rule.Rule;
import net.dg.generic.money.Money;
import org.junit.Before;

public abstract class AbstractDiscountStrategyTest {

    protected DiscountStrategy discountStrategy;
    protected Showing showing;

    @Before
    public void setUp() throws Exception {
        discountStrategy = createDiscountStrategy();
    }

    protected Rule createRule(final boolean isStatisfied) {
        return new Rule() {
                @Override
                public boolean isStatisfiedBy(Showing showing) {
                    return isStatisfied;
                }
            };
    }

    protected void createShowing(int fixedFee) {
        Movie movie = new Movie("", 120, Money.wons(fixedFee));
        showing = new Showing(movie, 0, null);
    }

    protected abstract DiscountStrategy createDiscountStrategy();
}
