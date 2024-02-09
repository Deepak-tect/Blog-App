package com.blog.blogingapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.blog.blogingapp.Entities.Comment;
import com.blog.blogingapp.Entities.Post;
import com.blog.blogingapp.Entities.User;

public interface CommentRepo extends JpaRepository<Comment , Integer> {
    public List<Comment> findByUser(User user);
    public List<Comment> findByPost(Post post);
}
