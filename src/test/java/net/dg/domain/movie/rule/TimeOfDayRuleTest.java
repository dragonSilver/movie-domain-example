package net.dg.domain.movie.rule;

import net.dg.domain.movie.Showing;
import net.dg.generic.base.DateTimeInterval;
import net.dg.generic.base.DayOfWeek;
import net.dg.generic.base.TimeInterval;
import org.junit.Test;

import static net.dg.generic.base.TimeInterval.of;
import static org.fest.assertions.Assertions.assertThat;

public class TimeOfDayRuleTest extends AbstractRuleTest{

    @Test
    public void test_timeOfDayRule_isStatisfiedBy() throws Exception {

        assertIsStatisfiedBetweenT18AndT20(
                DayOfWeek.TUE, of("T18", "T20"), true);

        assertIsStatisfiedBetweenT18AndT20(
                DayOfWeek.TUE, of("T18:00:01", "T19:59:59"), false);

        assertIsStatisfiedBetweenT18AndT20(
                DayOfWeek.WED, of("T18", "T21"), false);

        assertIsStatisfiedBetweenT18AndT20(
                DayOfWeek.TUE, of("T18", "T20:00:01"), true);

        assertIsStatisfiedBetweenT18AndT20(
                DayOfWeek.TUE, of("T17:59:59", "T20"), true);

    }

    private void assertIsStatisfiedBetweenT18AndT20(DayOfWeek day, TimeInterval timeRuleInterval, boolean expected) {
        DateTimeInterval playingInterval = DateTimeInterval.of("2014-10-28T18", "2014-10-28T20");
        Showing showing = createShowing(10, playingInterval);

        TimeOfDayRule timeOfDayRule = new TimeOfDayRule(day,timeRuleInterval);

        assertThat(timeOfDayRule.isStatisfiedBy(showing)).isEqualTo(expected);
    }


}
