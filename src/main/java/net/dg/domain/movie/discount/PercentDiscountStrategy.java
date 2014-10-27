package net.dg.domain.movie.discount;

import lombok.NoArgsConstructor;
import net.dg.domain.movie.Showing;
import net.dg.domain.movie.rule.Rule;
import net.dg.generic.money.Money;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("P")
@NoArgsConstructor
public class PercentDiscountStrategy extends DiscountStrategy {

    @Column
    private double percent;

    public PercentDiscountStrategy(double percent, List<Rule> rules) {
        super(rules);
        this.percent = BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100)).doubleValue();
    }

    @Override
    protected Money getDiscountedFee(Showing showing) {
        return showing.getFixedFee().minus(showing.getFixedFee().times(this.percent));
    }
}
