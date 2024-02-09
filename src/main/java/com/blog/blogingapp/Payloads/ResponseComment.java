package com.blog.blogingapp.Payloads;

public class ResponseComment {
    private int id;
    private String content;
    private ResponsePost post;
    private ResponseUser user;
    public ResponseComment() {
    }
    public ResponseComment(int id, String content, ResponsePost post, ResponseUser user) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.user = user;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public ResponsePost getPost() {
        return post;
    }
    public void setPost(ResponsePost post) {
        this.post = post;
    }
    public ResponseUser getUser() {
        return user;
    }
    public void setUser(ResponseUser user) {
        this.user = user;
    }
    
}
