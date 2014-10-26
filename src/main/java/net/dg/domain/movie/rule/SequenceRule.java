package net.dg.domain.movie.rule;

import net.dg.domain.movie.Showing;

public class SequenceRule extends Rule {
    private final int sequence;

    public SequenceRule(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isStatisfiedBy(Showing showing) {
        return showing.isSequence(sequence);
    }
}
