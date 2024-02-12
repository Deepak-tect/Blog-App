package com.blog.blogingapp.Services;

import java.util.List;

import com.blog.blogingapp.Payloads.ResponseUser;

public interface UserService {
    ResponseUser RegisterUser(ResponseUser user);
    ResponseUser createUser(ResponseUser user);
    ResponseUser updateUser(ResponseUser user , Integer id);
    ResponseUser getUserById(Integer userId);
    List<ResponseUser> getAllUser(); 
    void deleteUser(Integer id);    
}
