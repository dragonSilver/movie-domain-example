package net.dg.generic.base;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class TimeIntervalTest {

    @Test
    public void test_includes() throws Exception {

        assertIncludesBetweenT18AndT20("T18", "T20", true);
        assertIncludesBetweenT18AndT20("T18", "T19:59:59", false);
        assertIncludesBetweenT18AndT20("T18:00:01", "T19:59:59", false);
        assertIncludesBetweenT18AndT20("T17:59:59", "T20:00:01", true);
        assertIncludesBetweenT18AndT20("T21", "T23", false);
        assertIncludesBetweenT18AndT20("T21", "T0", false);

    }

    private void assertIncludesBetweenT18AndT20(String start, String end, boolean expected) {
        TimeInterval compareInterval = TimeInterval.of("T18", "T20");
        TimeInterval sut = TimeInterval.of(start, end);
        assertThat(sut.includes(compareInterval)).isEqualTo(expected);
    }
}