package com.blog.blogingapp.Contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogingapp.Payloads.ResponseUser;
import com.blog.blogingapp.Services.UserService;
import com.blog.blogingapp.Utils.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {
    

    @Autowired
    private UserService userService;

    
    @PostMapping("/")
    public ResponseEntity<ApiResponse<ResponseUser>> createUser(@Valid @RequestBody ResponseUser responseUser){
        ResponseUser result = userService.createUser(responseUser);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Created successfully"), HttpStatus.CREATED);
    } 

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseUser>> updatedUser(@PathVariable("id") int id, @Valid @RequestBody ResponseUser responseUser) {
        ResponseUser result = userService.updateUser(responseUser, id);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Updated successfully"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseUser>> getUserById(@PathVariable("id") int id) {
        ResponseUser result = userService.getUserById(id);
        ApiResponse<ResponseUser> apiResponse = new ApiResponse<>(200, result, "successfully fetched the user");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<ResponseUser>>> getAllUsers() {
        List<ResponseUser> result = userService.getAllUser();
        // ApiResponse<List<ResponseUser>> apiResponse = new ApiResponse<>(200, result, "successfully fetched user");
        return new ResponseEntity<>(new ApiResponse<>(200, result, "successfully fetched user"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse<ResponseUser>(200, null, "successfully deleted user"), HttpStatus.OK);
    }

    

}
