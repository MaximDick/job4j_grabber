package ru.job4jgrabber;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {

//        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
//        Elements row = doc.select(".postslisttopic");
//        for (Element td : row) {
//            Element href = td.child(0);
//            System.out.println(href.attr("href"));
//            System.out.println(href.text());
//        }

        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Element table = doc.select("table").get(2);
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            Element href = cols.get(1).child(0);
            Element vacancy = cols.get(5);

            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(vacancy.text());
        }
    }
}
