package net.dg.domain.movie.discount;

import net.dg.domain.movie.Movie;
import net.dg.domain.movie.Showing;
import net.dg.domain.movie.discount.DiscountStrategy;
import net.dg.domain.movie.discount.NoneDiscountStrategy;
import net.dg.generic.money.Money;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class NoneDiscountStrategyTest {

    @Test
    public void 할인되지않은_금액_계산() throws Exception {
        Movie movie = new Movie("", 120, Money.wons(10000));
        Showing showing = new Showing(movie);

        DiscountStrategy discountStrategy = new NoneDiscountStrategy();

        Money fee = discountStrategy.calculateFee(showing);

        assertThat(fee.getAmount()).isEqualTo(Money.wons(10000).getAmount());
    }

}
