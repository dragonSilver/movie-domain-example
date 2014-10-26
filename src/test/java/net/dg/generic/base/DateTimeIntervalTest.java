package net.dg.generic.base;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DateTimeIntervalTest {

    @Test
    public void test_includes() throws Exception {

        assertBetween20141026And20141028_Includes("2014-10-27T18", "2014-10-27T20", true);
        assertBetween20141026And20141028_Includes("2014-10-26T00", "2014-10-28T00", true);
        assertBetween20141026And20141028_Includes("2014-10-25T23", "2014-10-26T20", false);
        assertBetween20141026And20141028_Includes("2014-10-27T23", "2014-10-28T01", false);
    }

    private void assertBetween20141026And20141028_Includes(String start, String end, boolean expected) {
        DateTimeInterval playingInterval = DateTimeInterval.of(start, end);

        boolean include = DateTimeInterval.of("2014-10-26", "2014-10-28").includes(playingInterval);

        assertThat(include).isEqualTo(expected);
    }
}