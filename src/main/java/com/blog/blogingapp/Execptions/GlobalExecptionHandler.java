package com.blog.blogingapp.Execptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String , String>>> MethodArgNotValidException(MethodArgumentNotValidException exc){
        Map<String , String> m = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach((error)->{
            String FieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            m.put(FieldName, message);
        });
        return new ResponseEntity<>(new ApiResponse<Map<String , String>>(401, m, "Failed"), HttpStatus.BAD_REQUEST);
    }
}
