package net.dg.domain.movie.discount;

import net.dg.domain.movie.rule.Rule;
import net.dg.domain.movie.Showing;
import net.dg.generic.money.Money;

import java.util.List;

public abstract class DiscountStrategy {
    private final List<Rule> rules;

    public DiscountStrategy(List<Rule> rules) {
        this.rules = rules;
    }

    public Money calculateFee(Showing showing) {

        for(Rule each : rules) {
            if(each.isStatisfiedBy(showing)) {
                return getDiscountedFee(showing);
            }
        }


        return showing.getFixedFee();
    }

    protected abstract Money getDiscountedFee(Showing showing);


}
