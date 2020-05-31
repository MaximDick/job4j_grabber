package ru.job4jgrabber;

import java.util.List;

public interface Store {
    /**
     * Method should save data to our storage
     *
     * @param post - post
     */
    void save(Post post);

    /**
     * Method should return all element from storage
     *
     * @return - list of posts
     */
    List<Post> getAll();
    }
