package com.blog.blogingapp.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>(); 

    public Category() {
    }

    
    public Category(int id, String title, String description, List<Post> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public List<Post> getCategories() {
        return posts;
    }


    public void setCategories(List<Post> categories) {
        this.posts = posts;
    }


    public List<Post> getPosts() {
        return posts;
    }


    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    

}
