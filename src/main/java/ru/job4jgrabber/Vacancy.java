package ru.job4jgrabber;


import java.util.Date;

public class Vacancy {
    /**
     * название вакансии.*/
    private String name;
    /**
     * ссылка*/
    private String link;
    private Date data;

    public Vacancy(String name, String link, Date date) {
        this.name = name;
        this.link = link;
        this.data = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
