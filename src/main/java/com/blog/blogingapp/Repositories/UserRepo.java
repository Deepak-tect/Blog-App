package com.blog.blogingapp.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogingapp.Entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

// impelemented by proxy class at run time , hence during the use autowire
// we get the object of proxy class  
