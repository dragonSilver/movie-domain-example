package net.dg.domain.movie.discount;

import net.dg.domain.movie.Showing;
import net.dg.generic.money.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;

@Entity
@DiscriminatorValue("N")
public class NoneDiscountStrategy extends DiscountStrategy {
    public NoneDiscountStrategy() {
        super(Collections.EMPTY_LIST);
    }

    @Override
    protected Money getDiscountedFee(Showing showing) {
        return showing.getFixedFee();
    }
}
