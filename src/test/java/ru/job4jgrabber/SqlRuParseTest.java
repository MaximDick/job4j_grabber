package ru.job4jgrabber;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SqlRuParseTest {

    @Test
    public void whenReplaceTodayFromStringThenGetDate() {
        SqlRuParse parse = new SqlRuParse();
        Date time = Calendar.getInstance().getTime();
        String expected = new SimpleDateFormat("d MM yy").format(time);

        String result = parse.setToday();
        assertThat(result, is(expected));
    }

    @Test
    public void whenReplaceYesterdayFromStringThenGetDate() {
        SqlRuParse parser = new SqlRuParse();
        Date time = java.sql.Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        String expected = new SimpleDateFormat("d MM yy").format(time);

        String result = parser.setYesterday();
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertTimeFromStringTenGetDate() {
        SqlRuDate parser = new SqlRuDate();
        Date date = java.sql.Timestamp.valueOf(LocalDateTime.of(2020, 05, 18, 21, 19));

        Date result = parser.convertDate("18 май 20, 21:19");
        System.out.println(result);
        assertThat(result, is(date));

    }

}