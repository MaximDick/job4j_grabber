package ru.job4jgrabber;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlRuDate {

    /**
     * Конвертирует дату из строки в Date.
     * */
    public static Date convertDate(String stringDate) {
        Date date = null;
        String[] givenMonths = {"янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        String[] realMonths = {"01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12"};

        for (int i = 0; i < givenMonths.length; i++) {
            stringDate = stringDate.replace(givenMonths[i], realMonths[i]);
        }

        try {
            DateFormat format = new SimpleDateFormat("dd MM yy, HH:mm");
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
