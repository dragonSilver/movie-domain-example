package net.dg.domain.movie.rule;

import net.dg.domain.movie.Showing;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SequenceRuleTest extends AbstractRuleTest{

    @Test
    public void 시퀀스룰에_상영차수가_해당하는지_확인한다() throws Exception {
        assertSequenceRule(5, 10, false);
        assertSequenceRule(5, 2, false);
        assertSequenceRule(1, 1, true);
    }

    private void assertSequenceRule(int sequence, int showingSequence, boolean expected) {
        Showing showing = createShowing(showingSequence, null);

        SequenceRule sequenceRule = new SequenceRule(sequence);

        assertThat(sequenceRule.isStatisfiedBy(showing)).isEqualTo(expected);
    }
}