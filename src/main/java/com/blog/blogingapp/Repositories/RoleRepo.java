package com.blog.blogingapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.blogingapp.Entities.Roles;

public interface RoleRepo extends JpaRepository<Roles , Integer> {
    
}
