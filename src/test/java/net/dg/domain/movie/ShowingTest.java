package net.dg.domain.movie;

import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

public class ShowingTest {

    @Test
    public void test_reserve() throws Exception {
        Movie movie = new Movie("", 120, Money.wons(10000));
        Customer customer = new Customer("name");

        Showing showing = new Showing(movie, 1, DateTimeInterval.of("2014-10-28T18", "2014-10-28T20"));

        Reservation reservation = showing.reserve(customer, 2);

        assertThat(reservation.getCustomer()).isEqualTo(customer);
        assertThat(reservation.getAudienceCount()).isEqualTo(2);
        assertThat(reservation.getFee().getAmount()).isEqualTo(BigDecimal.valueOf(20000));
    }

}