package net.dg.generic.util;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DateUtilTest {


    @Test
    public void test_toDateString() throws Exception {

        DateTime dateTime = DateTime.parse("2014-10-28T18");

        assertThat(DateUtil.toDateString(dateTime)).contains("2014-10-28 18");

    }
}