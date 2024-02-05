package com.blog.blogingapp.Execptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blogingapp.Utils.ApiResponse;

@RestControllerAdvice
public class GlobalExecptionHandler {
    
    @ExceptionHandler(ResourceNotFoundExecption.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFound(ResourceNotFoundExecption resourceNotFoundExecption){
        String message = resourceNotFoundExecption.getMessage();
        return new ResponseEntity<>(new ApiResponse<>(500, null, message), HttpStatus.NOT_FOUND);
    }
}
