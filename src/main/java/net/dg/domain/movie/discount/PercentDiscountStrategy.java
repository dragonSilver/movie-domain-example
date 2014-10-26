package net.dg.domain.movie.discount;

import net.dg.domain.movie.Showing;
import net.dg.domain.movie.rule.Rule;
import net.dg.generic.money.Money;

import java.math.BigDecimal;
import java.util.List;

public class PercentDiscountStrategy extends DiscountStrategy {
    private final double percent;

    public PercentDiscountStrategy(double percent, List<Rule> rules) {
        super(rules);
        this.percent = BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100)).doubleValue();
    }

    @Override
    protected Money getDiscountedFee(Showing showing) {
        return showing.getFixedFee().minus(showing.getFixedFee().times(this.percent));
    }
}
