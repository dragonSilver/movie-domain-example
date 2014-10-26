package net.dg.domain.movie.discount;

import com.google.common.collect.Lists;
import net.dg.domain.movie.rule.Rule;
import net.dg.generic.money.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;

public class PercentDiscountStrategyTest extends AbstractDiscountStrategyTest {

    private final int fixedFee = 10000;
    private final double percent = 10;
    
    
    @Test
    public void 할인_10퍼센트() throws Exception {
        Money fee = discountStrategy.calculateFee(showing);
        assertThat(fee.getAmount()).isEqualTo(BigDecimal.valueOf(9000));
    }

    @Override
    protected DiscountStrategy createDiscountStrategy() {
        createShowing(fixedFee);
        ArrayList<Rule> rules = Lists.newArrayList(createRule(true));
        System.out.println(percent);
        return new PercentDiscountStrategy(percent, rules);
    }

}
