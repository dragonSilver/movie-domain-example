package net.dg.domain.movie.rule;

import lombok.NoArgsConstructor;
import net.dg.domain.movie.Showing;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
@NoArgsConstructor
public class SequenceRule extends Rule {

    @Column
    private Integer sequence;

    public SequenceRule(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isStatisfiedBy(Showing showing) {
        return showing.isSequence(sequence);
    }
}
