package com.blog.blogingapp.Services.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.blogingapp.Constants.AppConstant;
import com.blog.blogingapp.Entities.Roles;
import com.blog.blogingapp.Entities.User;
import com.blog.blogingapp.Execptions.ResourceNotFoundExecption;
import com.blog.blogingapp.Payloads.ResponseUser;
import com.blog.blogingapp.Repositories.RoleRepo;
import com.blog.blogingapp.Repositories.UserRepo;
import com.blog.blogingapp.Services.UserService;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public User responseToUser(ResponseUser responseUser){
        // User user = new User(responseUser.getId(), responseUser.getName() , responseUser.getEmail(), responseUser.getPassword() , responseUser.getAbout());
        User user = this.modelMapper.map(responseUser, User.class);
        return user;
    }
    public ResponseUser userToResponse(User user){
        // ResponseUser responseUser = new ResponseUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAbout());
        ResponseUser responseUser = this.modelMapper.map(user, ResponseUser.class);
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
    @Override
    public ResponseUser RegisterUser(ResponseUser userRes) {
        User user = this.modelMapper.map(userRes, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Roles role = roleRepo.findById(AppConstant.User).get();
        user.getRoles().add(role);
        User newUser = userRepo.save(user);
        return this.modelMapper.map(newUser, ResponseUser.class);
    }
    
}
