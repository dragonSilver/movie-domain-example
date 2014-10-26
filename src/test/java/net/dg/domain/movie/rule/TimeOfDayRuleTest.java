package net.dg.domain.movie.rule;

import net.dg.domain.movie.Showing;
import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.base.DayOfWeek;
import org.junit.Test;

import static net.dg.generic.base.DateTimeInterval.*;
import static org.fest.assertions.Assertions.assertThat;

public class TimeOfDayRuleTest extends AbstractRuleTest{

    @Test
    public void test_timeOfDayRule_isStatisfiedBy() throws Exception {

        assertIsStatisfiedBetween1028T18And1028T20(
                DayOfWeek.TUE, of("2014-10-28T18", "2014-10-28T20"), true);
        assertIsStatisfiedBetween1028T18And1028T20(
                DayOfWeek.WED, of("2014-10-28T18", "2014-10-28T20"), false);
        assertIsStatisfiedBetween1028T18And1028T20(
                DayOfWeek.TUE, of("2014-10-28T18", "2014-10-28T19:59"), false);

    }

    private void assertIsStatisfiedBetween1028T18And1028T20(DayOfWeek day, DateTimeInterval timeRuleInterval, boolean expected) {
        DateTimeInterval playingInterval = of("2014-10-28T18", "2014-10-28T20");
        Showing showing = createShowing(10, playingInterval);

        TimeOfDayRule timeOfDayRule = new TimeOfDayRule(day,timeRuleInterval);

        assertThat(timeOfDayRule.isStatisfiedBy(showing)).isEqualTo(expected);
    }


}
