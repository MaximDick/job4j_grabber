package ru.job4jgrabber;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SqlRuParse {
//    public static void main(String[] args) throws Exception {


//        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
//        Element table = doc.select("table").get(2);
//        Elements rows = table.select("tr");
//        for (int i = 1; i < rows.size(); i++) {
//            Element row = rows.get(i);
//            Elements cols = row.select("td");
//            Element href = cols.get(1).child(0);
//            Element data = cols.get(5);
//
//            System.out.println(href.attr("href"));
//            System.out.println(href.text());
//            System.out.println(data.text());
//        }
//    }

    /**
     * Парсит сайт.
     * Вытаскивает название вакансии ссылку и дату.
     * Меняет заменяет "сегодня" и "вчера" другим представлением в виде "d MM yy".
     * Конвертирует дату в формат Date.
     * Добавляет в список и возвращает его.
     * @param url - ссылка ссайта в строковом представлении.
     * @return listVacancies*/
    public List<Vacancy> parseVacancy(String url, int pages) {
        List<Vacancy> listVacancies = new ArrayList<>();
        Document doc = null;
        Date date;
        System.out.println("прочитать страниц " + pages);
        for (int p = 1; p <= pages; p++) {
            try {
                doc = Jsoup.connect(url + 1).get();
                Elements forumTable = doc.getElementsByClass("forumTable");
                Elements rows = forumTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    String name = row.child(1).getElementsByTag("a").first().text();
                    String link = row.getElementsByTag("a").attr("href");
                    String create = row.child(5).text();
                    if (create.contains("сегодня")) {
                        create = create.replace("сегодня", setToday());
                    }
                    if (create.contains("вчера")) {
                        create = create.replace("вчера", setYesterday());
                    }

                    date = convertDate(create);
                    listVacancies.add(new Vacancy(name, link, date));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listVacancies;
    }
        /**
         * Возвращает сегодняшнюю дату в виде "d MM yy".
         * @return timeStamp дата в формате строки
         */
        public String setToday() {
            Date time = Calendar.getInstance().getTime();
            String timeStamp = new SimpleDateFormat("d MM yy").format(time);
            return timeStamp;
        }

        /**
         * Возвращает вчерашнюю дату в виде "d MM yy".
         * @return timeStamp дата в формате строки
         */
        public String setYesterday() {
            LocalDateTime in = LocalDateTime.now().minusDays(1);
            Date time = java.sql.Timestamp.valueOf(in);
            String timeStamp = new SimpleDateFormat("d MM yy").format(time);
            return timeStamp;
        }

        /**
         * Конвертирует дату из строки в Date.
         * */
        public Date convertDate(String stringDate) {
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

