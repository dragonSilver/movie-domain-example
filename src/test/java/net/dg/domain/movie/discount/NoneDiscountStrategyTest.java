package net.dg.domain.movie.discount;

import net.dg.generic.money.Money;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class NoneDiscountStrategyTest extends AbstractDiscountStrategyTest{

    private int fixedAmount = 10000;

    @Test
    public void 할인되지않은_금액_계산() throws Exception {
        Money fee = discountStrategy.calculateFee(showing);
        assertThat(fee.getAmount()).isEqualTo(Money.wons(fixedAmount).getAmount());
    }

    @Override
    protected DiscountStrategy createDiscountStrategy() {
        createShowing(fixedAmount);
        return new NoneDiscountStrategy();
    }
}
