package net.dg.domain.movie;


import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of={"name"})
@Getter
public class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }
}
