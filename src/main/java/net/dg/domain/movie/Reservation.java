package net.dg.domain.movie;

import lombok.Getter;
import net.dg.generic.money.Money;

@Getter
public class Reservation {

    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;
    private final Money fee;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.fee = showing.calculateFee().times(audienceCount);
        this.audienceCount = audienceCount;
    }
}
