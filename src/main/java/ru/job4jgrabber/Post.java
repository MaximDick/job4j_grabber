package ru.job4jgrabber;

import java.util.Date;
import java.util.Objects;

/**
 * Класс описывающий модель поста.*/
public class Post {

    String topicPost; //тема поста
    String desc; //описание
    String nameAuthor; //автор
    Date dateCreated; //дата создания

    public Post(String topicPost, String desc, String nameAutor, Date dateCreated) {
        this.topicPost = topicPost;
        this.desc = desc;
        this.nameAuthor = nameAutor;
        this.dateCreated = dateCreated;
    }

    public String getTopicPost() {
        return topicPost;
    }

    public void setTopicPost(String topicPost) {
        this.topicPost = topicPost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Post post = (Post) o;

        if (!Objects.equals(topicPost, post.topicPost)) {
            return false;
        }
        if (!Objects.equals(desc, post.desc)) {
            return false;
        }
        if (!Objects.equals(nameAuthor, post.nameAuthor)) {
            return false;
        }
        return Objects.equals(dateCreated, post.dateCreated);
    }

    @Override
    public int hashCode() {
        int result = topicPost != null ? topicPost.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (nameAuthor != null ? nameAuthor.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" + "topicPost='"
                + topicPost + '\''
                + ", desc='" + desc + '\''
                + ", nameAuthor='" + nameAuthor + '\''
                + ", dateCreated=" + dateCreated + '}';
    }
}
