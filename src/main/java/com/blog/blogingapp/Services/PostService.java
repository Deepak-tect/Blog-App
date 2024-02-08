package com.blog.blogingapp.Services;

import java.util.List;

import com.blog.blogingapp.Payloads.ApiPageResponse;
import com.blog.blogingapp.Payloads.ResponsePost;

public interface PostService {
    ResponsePost createPost(ResponsePost post, int userId , int categoryId);
    ResponsePost updatePost(ResponsePost post, int id);
    void deletePost(int id);
    ApiPageResponse<ResponsePost> getAllPost(int pageNumber, int pageSize, String sortBy);
    ResponsePost getPostById(int id);
    List<ResponsePost> getPostOfUser(int userId);
    List<ResponsePost> getPostOfCategory(int categoryId);

    List<ResponsePost> getPostBytitle(String keyword);

}
