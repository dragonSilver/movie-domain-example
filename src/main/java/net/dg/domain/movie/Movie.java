package net.dg.domain.movie;

import lombok.Getter;
import net.dg.domain.movie.discount.DiscountStrategy;
import net.dg.domain.movie.discount.NoneDiscountStrategy;
import net.dg.generic.money.Money;

@Getter
public class Movie {
    private final String title;
    private final int runningTime;
    private final Money fee;
    private final DiscountStrategy discountStrategy;

    public Movie(String title, int runningTime, Money fee) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountStrategy = new NoneDiscountStrategy();
    }

    public Movie(String title, int runningTime, Money fee, DiscountStrategy discountStrategy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountStrategy = discountStrategy;
    }

    public Money calculateFee(Showing showing) {
        return discountStrategy.calculateFee(showing);
    }
}
