package com.blog.blogingapp.Contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogingapp.Payloads.ResponseUser;
import com.blog.blogingapp.Security.CustomUserDetailService;
import com.blog.blogingapp.Security.JwtHelper;
import com.blog.blogingapp.Services.UserService;
import com.blog.blogingapp.Utils.ApiResponse;
import com.blog.blogingapp.Utils.JwtRequest;
import com.blog.blogingapp.Utils.JwtResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private CustomUserDetailService customeUserDetailService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;


    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        System.out.println("controller " + request.getEmail());
        // System.out.println("controller " + u.findByEmail(request.getEmail()).get().getPassword());
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = customeUserDetailService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            System.out.println("--------------inside---------------");
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse<ResponseUser>> createUser(@RequestBody ResponseUser user){
        ResponseUser result = this.userService.RegisterUser(user);
        return new ResponseEntity<>(new ApiResponse<>(201, result, "Successfully created user"), HttpStatus.CREATED);
    }
}
