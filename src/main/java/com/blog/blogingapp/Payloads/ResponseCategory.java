package com.blog.blogingapp.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ResponseCategory {
    private int id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(max=150, message = "About should not exceed 150 character")
    private String description;
    public ResponseCategory(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public ResponseCategory() {
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
    
    
}
