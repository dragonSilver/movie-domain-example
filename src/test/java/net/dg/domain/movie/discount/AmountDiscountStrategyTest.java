package net.dg.domain.movie.discount;

import com.google.common.collect.Lists;
import net.dg.domain.movie.Movie;
import net.dg.domain.movie.Showing;
import net.dg.domain.movie.discount.AmountDiscountStrategy;
import net.dg.domain.movie.rule.Rule;
import net.dg.generic.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class AmountDiscountStrategyTest {

    @Test
    public void 이천원을_할인한다() throws Exception {
        Movie movie = new Movie("", 120, Money.wons(22000));
        Showing showing = new Showing(movie);

        List<Rule> rules = Lists.newArrayList(createRuleForPass());

        AmountDiscountStrategy discountStrategy = new AmountDiscountStrategy(rules, Money.wons(2000));

        Money fee = discountStrategy.calculateFee(showing);

        assertThat(fee.getAmount()).isEqualTo(BigDecimal.valueOf(20000));
    }

    private Rule createRuleForPass() {
        return new Rule() {
                @Override
                public boolean isStatisfiedBy(Showing showing) {
                    return true;
                }
            };
    }

}
