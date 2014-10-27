package net.dg.domain.movie;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMER_ID")
    private Long id;

    @Column
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
