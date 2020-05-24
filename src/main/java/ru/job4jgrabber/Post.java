package ru.job4jgrabber;

import java.util.Date;
import java.util.Objects;

/**
 * Класс описывающий модель поста.*/
public class Post {


    String desc; //описание
    String nameAuthor; //автор
    Date dateCreated; //дата создания

    public Post(String nameAuthor, String desc, Date dateCreated) {
        this.nameAuthor = nameAuthor;
        this.desc = desc;
        this.dateCreated = dateCreated;


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
        int result = desc != null ? desc.hashCode() : 0;
        result = 31 * result + (nameAuthor != null ? nameAuthor.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" + "topicPost='"
                + ", desc='" + desc + '\n'
                + ", nameAuthor=" + nameAuthor + '\n'
                + ", dateCreated=" + dateCreated + '}';
    }
}
