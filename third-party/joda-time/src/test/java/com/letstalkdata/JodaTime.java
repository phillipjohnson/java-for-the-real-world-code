package com.letstalkdata;

import org.joda.time.*;
import org.junit.Test;

public class JodaTime {

    @Test
    public void instants() {
        assert 1483246800000L == Instant.parse("2017-01-01").getMillis();

        assert 1483246800000L == Instant.parse("2017-01-02")
                .minus(24 * 60 * 60 * 1000)
                .getMillis();

        assert Instant.parse("1900-01-01").getMillis() < 0;
    }

    @Test
    public void dates() {
        DateTime dt = new DateTime(2017, 1, 1, 0, 1, 23);
        System.out.println(dt.toString());
        assert dt.toString().startsWith("2017-01-01T00:01:23.000");

        assert DateTime.now().isAfter(Instant.parse("2017-01-01"));
    }

    @Test
    public void periods() {
        DateTime start = new DateTime(2017, 1, 1, 0, 0);
        DateTime end = new DateTime(2018, 1, 1, 0, 0);
        assert 1 == Years.yearsBetween(start, end).getYears();
        assert 525600 == Minutes.minutesBetween(start, end).getMinutes();
    }

    @Test
    public void zones() {
        DateTimeZone UTC = DateTimeZone.UTC;
        DateTimeZone NYC = DateTimeZone.forID("America/New_York");

        DateTime utc = new DateTime(2017, 1, 1, 0, 0, UTC);
        DateTime nyc = new DateTime(2017, 1, 1, 0, 0, NYC);

        assert nyc.getMillis() == utc.plusHours(5).getMillis();
    }
}
