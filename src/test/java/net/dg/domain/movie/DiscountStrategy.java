package net.dg.domain.movie;

import net.dg.generic.money.Money;

import java.util.List;

public class DiscountStrategy {
    private final List<Rule> rules;

    public DiscountStrategy(List<Rule> rules) {
        this.rules = rules;
    }

    public Money calculateFee(Showing showing) {

        return showing.getFixedFee();
    }
}
