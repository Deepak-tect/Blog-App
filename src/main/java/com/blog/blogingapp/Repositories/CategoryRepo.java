package com.blog.blogingapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogingapp.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
}
