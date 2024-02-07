package com.blog.blogingapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogingapp.Entities.Category;
import com.blog.blogingapp.Entities.Post;
import com.blog.blogingapp.Entities.User;

import java.util.List;


public interface PostRepo extends JpaRepository<Post , Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
