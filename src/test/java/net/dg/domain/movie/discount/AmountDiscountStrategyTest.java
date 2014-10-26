package net.dg.domain.movie.discount;

import com.google.common.collect.Lists;
import net.dg.domain.movie.rule.Rule;
import net.dg.generic.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class AmountDiscountStrategyTest extends AbstractDiscountStrategyTest{

    private final int fixedFee = 22000;
    private int discountFee = 2000;

    @Test
    public void 이천원을_할인한다() throws Exception {

        Money fee = discountStrategy.calculateFee(showing);

        assertThat(fee.getAmount()).isEqualTo(BigDecimal.valueOf(20000));
    }

    @Override
    protected DiscountStrategy createDiscountStrategy() {
        createShowing(fixedFee);
        List<Rule> rules = Lists.newArrayList(createRule(true));

        return new AmountDiscountStrategy(rules, Money.wons(discountFee));
    }
}
