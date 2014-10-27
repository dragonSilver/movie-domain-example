package net.dg.domain.movie.discount;

import lombok.NoArgsConstructor;
import net.dg.domain.movie.rule.Rule;
import net.dg.domain.movie.Showing;
import net.dg.generic.money.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("A")
@NoArgsConstructor
public class AmountDiscountStrategy extends DiscountStrategy{

    @Embedded
    private Money discountAmount;

    public AmountDiscountStrategy(List<Rule> rules, Money discountAmount) {
        super(rules);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountedFee(Showing showing) {
        return showing.getFixedFee().minus(discountAmount);
    }
}
