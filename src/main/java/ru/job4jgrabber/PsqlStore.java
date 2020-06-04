package ru.job4jgrabber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException();
        }


    }

    @Override
    public void save(Post post) {
        boolean result = false;
        try (PreparedStatement statement = cnn.prepareStatement("insert into post(name, text,created) values(?,?,?,?)")) {
            statement.setString(1, post.getNameAuthor());
            statement.setString(2, post.getDesc());
            statement.setTimestamp(3, Timestamp.valueOf(String.valueOf(post.getDateCreated())));
            result = statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try (Statement statement = cnn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from post");
            while (resultSet.next()) {
                result.add(new Post(resultSet.getString("name"),
                        resultSet.getString("desc"),
                        resultSet.getTimestamp("created").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Post findById(String id) {
        Post result = null;
        try (PreparedStatement statement = cnn.prepareStatement("selet * from where id = ?")) {
            statement.setInt(1, Integer.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new Post(resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getTimestamp("created").toLocalDateTime());
            } if (result == null) {
                throw new NoSuchElementException("Post with id not found");
            }
        } catch (NoSuchElementException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {

    }

}
