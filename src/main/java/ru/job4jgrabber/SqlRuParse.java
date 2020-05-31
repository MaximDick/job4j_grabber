package ru.job4jgrabber;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SqlRuParse {


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

                    date = SqlRuDate.convertDate(create);
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
         * метод Возвращает список заполненных постов.
         * @param urlPost ссылка на форум.
         * @return listPost - возвращает список постов (автор, описание, дата создания).
         * */

        public  List<Post>  gettingPostDetails(String urlPost) throws IOException {
            List<Post> listPost = new ArrayList<>();
            Document doc = Jsoup.connect(urlPost).get();
            Elements row = doc.select("table.msgTable");
            for (int i = 0; i < row.size(); i++) {
                Element elem = row.get(i);
                String strDesc = elem.child(0).child(1).child(1).text();
                String author = elem.child(0).child(1).child(0).child(0).text();
                String dataStr = elem.child(0).child(2).child(0).text();
                String stDate = dataStr.substring(0, dataStr.indexOf('['));
                Date date = SqlRuDate.convertDate(stDate);

                listPost.add(new Post(author, strDesc, date));
            }
            return listPost;
        }



    public static void main(String[] args) throws IOException {
            String url = "https://www.sql.ru/forum/1322588/alfa-bank-vakansiya-t-sql-razrabotchika";
            SqlRuParse sq = new SqlRuParse();

    }

    }
