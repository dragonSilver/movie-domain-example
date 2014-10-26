package net.dg.domain.movie.discount;

import net.dg.domain.movie.Showing;
import net.dg.generic.money.Money;

import java.util.Collections;

public class NoneDiscountStrategy extends DiscountStrategy {
    public NoneDiscountStrategy() {
        super(Collections.EMPTY_LIST);
    }

    @Override
    protected Money getDiscountedFee(Showing showing) {
        return showing.getFixedFee();
    }
}
