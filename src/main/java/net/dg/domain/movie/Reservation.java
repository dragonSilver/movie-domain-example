package net.dg.domain.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dg.generic.money.Money;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
@NoArgsConstructor
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Showing showing;

    @Column
    private Integer audienceCount;

    @Embedded
    private Money fee;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.fee = showing.calculateFee().times(audienceCount);
        this.audienceCount = audienceCount;
    }
}
