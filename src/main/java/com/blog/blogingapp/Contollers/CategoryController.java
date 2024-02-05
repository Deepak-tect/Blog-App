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

import com.blog.blogingapp.Payloads.ResponseCategory;
import com.blog.blogingapp.Services.CategoryService;
import com.blog.blogingapp.Utils.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<ResponseCategory>> createCategory(@Valid @RequestBody ResponseCategory responseCategory){
        ResponseCategory result = this.categoryService.createCategory(responseCategory);
        return new ResponseEntity<>(new ApiResponse<>(201, result, "Category created successfully"),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseCategory>> updateCategory(@PathVariable int id, @Valid @RequestBody ResponseCategory responseCategory) {
        ResponseCategory updatedCategory = this.categoryService.updateCategory(responseCategory, id);
        return new ResponseEntity<>(new ApiResponse<>(200, updatedCategory, "Successfully updated the category"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseCategory>> getCategoryById(@PathVariable int id) {
        ResponseCategory result = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched category"), HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<ResponseCategory>>> getCategories() {
        List<ResponseCategory> result = this.categoryService.getAllCategory();
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched category"), HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse<>(200, null, "successfully deleted category"), HttpStatus.OK);
    }


}
