package net.dg.domain.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dg.domain.movie.discount.DiscountStrategy;
import net.dg.domain.movie.discount.NoneDiscountStrategy;
import net.dg.generic.money.Money;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "MOVIE")
@JsonIgnoreProperties(value = {"discountStrategy", "fee"})
@NoArgsConstructor
@Getter
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID")
    private Long id;

    @Column
    private String title;

    @Column
    private Integer runningTime;

    @Embedded
    private Money fee;

    @ManyToOne(cascade = CascadeType.ALL)
    private DiscountStrategy discountStrategy;

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

    public BigDecimal getFixedAmount() {
        return fee.getAmount().setScale(0);
    }

    public Money calculateFee(Showing showing) {
        return discountStrategy.calculateFee(showing);
    }
}
