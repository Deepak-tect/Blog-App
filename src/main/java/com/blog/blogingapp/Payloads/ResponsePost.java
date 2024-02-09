package com.blog.blogingapp.Payloads;

public class ResponsePost {
    private int id;
    private String title;
    private String content;
    private String imageName;
    private ResponseUser user; // to use ResponseUser because to remove infinite loop
    private ResponseCategory category;
    public ResponsePost() {
    }
    
    public ResponsePost(int id, String title, String content, String imageName, ResponseUser user, ResponseCategory category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageName = imageName;
        this.user = user;
        this.category = category;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ResponseUser getUser() {
        return user;
    }

    public void setUser(ResponseUser user) {
        this.user = user;
    }

    public ResponseCategory getCategory() {
        return category;
    }

    public void setCategory(ResponseCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ResponsePost [id=" + id + ", title=" + title + ", content=" + content + ", imageName=" + imageName
                + ", user=" + user + ", category=" + category + "]";
    }
    
    
    
}
