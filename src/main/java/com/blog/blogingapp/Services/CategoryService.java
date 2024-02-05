package com.blog.blogingapp.Services;

import java.util.*;

import com.blog.blogingapp.Payloads.ResponseCategory;

public interface CategoryService {
    public ResponseCategory createCategory(ResponseCategory responseCategory);

    public ResponseCategory updateCategory(ResponseCategory responseCategory , int id);

    public ResponseCategory getCategoryById(int id);

    public List<ResponseCategory> getAllCategory();

    public void deleteCategory(int id);
}
