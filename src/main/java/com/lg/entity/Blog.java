package com.lg.entity;

/**
 * Created by liuguo on 2017/3/27.
 */
import org.apache.ibatis.type.Alias;

import java.util.List;
@Alias("blog")
public class Blog {

    private int id;
    private String title;
    private Author author;
    private List<Post> posts;

    public Blog() {
    }

    public Blog(int id, String title, Author author, List<Post> posts) {
        this();
        this.id = id;
        this.title = title;
        this.author = author;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Blog: " + id + " : " + title + " (" + author + ")";
    }
}
