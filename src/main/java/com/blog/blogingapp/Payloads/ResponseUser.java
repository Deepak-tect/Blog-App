package com.blog.blogingapp.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ResponseUser {
    
    private int id;

    @NotEmpty
    private String name;

    @Email(message = "Email address is not valid")
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min=4 , max=15, message = "Password must be greater then 3 character and must be smaller then 15 character")
    private String password;

    @NotEmpty
    @Size(max=150, message = "About should not exceed 150 character")
    private String about;

    public ResponseUser() {
    }
    public ResponseUser(int id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    
}
