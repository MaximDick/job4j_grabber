package ru.job4jgrabber;

import java.util.List;

public interface Parse {
    /**
     * @param  link - ссылка на сайт.
     * Метод list загружает список всех постов.
     */
    List<Post> list(String link);

    /**
     * @param  link - ссылка на сайт.
     * Метод detail загружает детали одного поста.
     * */
    Post detail(String link);
}
