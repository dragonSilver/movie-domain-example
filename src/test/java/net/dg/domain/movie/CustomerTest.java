package net.dg.domain.movie;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void test_getter() throws Exception {

        String name = "최용은";
        Customer customer = new Customer(name);

        assertThat(customer.getName()).isEqualTo(name);
    }
}