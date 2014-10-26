package net.dg.domain.movie.discount;

import net.dg.domain.movie.rule.Rule;
import net.dg.domain.movie.Showing;
import net.dg.generic.money.Money;

import java.util.List;

public class AmountDiscountStrategy extends DiscountStrategy{

    private final Money discountAmount;

    public AmountDiscountStrategy(List<Rule> rules, Money discountAmount) {
        super(rules);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountedFee(Showing showing) {
        return showing.getFixedFee().minus(discountAmount);
    }
}
