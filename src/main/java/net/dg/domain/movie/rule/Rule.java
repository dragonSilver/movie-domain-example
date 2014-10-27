package net.dg.domain.movie.rule;

import lombok.Getter;
import net.dg.domain.movie.Showing;

import javax.persistence.*;

@Entity
@Table(name = "RULE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE")
@Getter
public abstract class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RULE_ID")
    private Long id;

    public abstract boolean isStatisfiedBy(Showing showing);
}
