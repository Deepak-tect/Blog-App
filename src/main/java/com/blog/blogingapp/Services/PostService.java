package com.blog.blogingapp.Services;

import java.util.List;

import com.blog.blogingapp.Payloads.ResponsePost;

public interface PostService {
    ResponsePost createPost(ResponsePost post, int userId , int categoryId);
    ResponsePost updatePost(ResponsePost post, int id);
    void deletePost(int id);
    List<ResponsePost> getAllPost();
    ResponsePost getPostById(int id);
    List<ResponsePost> getPostOfUser(int userId);
    List<ResponsePost> getPostOfCategory(int categoryId);

}
