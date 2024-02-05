package com.blog.blogingapp.Services.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogingapp.Entities.User;
import com.blog.blogingapp.Execptions.ResourceNotFoundExecption;
import com.blog.blogingapp.Payloads.ResponseUser;
import com.blog.blogingapp.Repositories.UserRepo;
import com.blog.blogingapp.Services.UserService;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    public User responseToUser(ResponseUser responseUser){
        User user = new User(responseUser.getId(), responseUser.getName() , responseUser.getEmail(), responseUser.getPassword() , responseUser.getAbout());
        return user;
    }
    public ResponseUser userToResponse(User user){
        ResponseUser responseUser = new ResponseUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAbout());
        return responseUser;
    }

    @Override
    public ResponseUser createUser(ResponseUser responseUser) {
        User user = responseToUser(responseUser);
        User createdUser = userRepo.save(user);
        return userToResponse(createdUser);

    }

    @Override
    public ResponseUser updateUser(ResponseUser responseUser, Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(responseUser.getName());
            user.setName(responseUser.getName());
            user.setEmail(responseUser.getEmail());
            user.setPassword(responseUser.getPassword());
            user.setAbout(responseUser.getAbout());
            userRepo.save(user);
            return userToResponse(user);
        }
        throw new ResourceNotFoundExecption("User", "id", id);
    }

    @Override
    public ResponseUser getUserById(Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()){
            return userToResponse(optionalUser.get());
        }
        throw new ResourceNotFoundExecption("User", "id", userId);
    }

    @Override
    public List<ResponseUser> getAllUser() {
        List<User> users = userRepo.findAll();
        if(users.size() > 0){
            List<ResponseUser> responseUsers = new ArrayList<ResponseUser>();
            for(User user : users){
                responseUsers.add(userToResponse(user));
            }
            return responseUsers;
        }
        throw new ResourceNotFoundExecption("User", "", 0);

    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            userRepo.delete(optionalUser.get());
            return ;
        }
        throw new ResourceNotFoundExecption("User", "id", id);

    }
    
}
