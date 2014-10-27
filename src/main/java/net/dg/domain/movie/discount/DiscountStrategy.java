package net.dg.domain.movie.discount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dg.domain.movie.rule.Rule;
import net.dg.domain.movie.Showing;
import net.dg.generic.money.Money;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DISCOUNT_STRATEGY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE")
@NoArgsConstructor
@Getter
public abstract class DiscountStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DISCOUNT_ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rule> rules;

    public DiscountStrategy(List<Rule> rules) {
        this.rules = rules;
    }

    public Money calculateFee(Showing showing) {

        for(Rule each : rules) {
            if(each.isStatisfiedBy(showing)) {
                return getDiscountedFee(showing);
            }
        }
        return showing.getFixedFee();
    }

    protected abstract Money getDiscountedFee(Showing showing);


}
