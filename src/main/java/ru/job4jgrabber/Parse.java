package ru.job4jgrabber;

import java.util.List;

public interface Parse {
    List<Post> list (String link);
    Post detail(String link);
}
