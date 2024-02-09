package com.blog.blogingapp.Contollers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogingapp.Payloads.ApiPageResponse;
import com.blog.blogingapp.Payloads.ResponsePost;
import com.blog.blogingapp.Services.PostService;
import com.blog.blogingapp.Services.UploadFile;
import com.blog.blogingapp.Utils.ApiResponse;



@RestController
@RequestMapping("/api")
public class PostController {
    
    @Autowired
    private PostService postService;
    @Autowired
    private UploadFile uploadFile;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<ApiResponse<ResponsePost>> createPost(@RequestBody ResponsePost responsePost, @PathVariable int userId , @PathVariable int categoryId) {
        ResponsePost result = this.postService.createPost(responsePost, userId , categoryId);
        
        return new ResponseEntity<>(new ApiResponse<>(201, result, "Successfully added user"), HttpStatus.CREATED);
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<ApiResponse<ResponsePost>> updatePost(@RequestBody ResponsePost responsePost, @PathVariable int id) {
        ResponsePost result = this.postService.updatePost(responsePost, id);
        
        return new ResponseEntity<>(new ApiResponse<>(201, result, "Successfully updated user"), HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<ApiResponse<ApiPageResponse<ResponsePost>>> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber , 
        @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id" , required = false) String sortBy
        ) {
        ApiPageResponse<ResponsePost> result = this.postService.getAllPost(pageNumber , pageSize, sortBy);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched Post "), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<ApiResponse<ResponsePost>> getPostById(@PathVariable int id) {
        ResponsePost result = this.postService.getPostById(id);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched Post"), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/post")
    public ResponseEntity<ApiResponse<List<ResponsePost>>> getPostByUser(@PathVariable int id) {
        List<ResponsePost> result = this.postService.getPostOfUser(id);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched post using user"), HttpStatus.OK);
    }

    @GetMapping("/category/{id}/post")
    public ResponseEntity<ApiResponse<List<ResponsePost>>> getPostByCategory(@PathVariable int id) {
        List<ResponsePost> result = this.postService.getPostOfCategory(id);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched post using category"), HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<ApiResponse<List<ResponsePost>>> getMethodName(@PathVariable String keyword) {
        List<ResponsePost> result = this.postService.getPostBytitle(keyword);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "Successfully fetched data"),HttpStatus.OK);
    }

    @PostMapping("/post/{id}/image")
    public ResponseEntity<ApiResponse<ResponsePost>> uploadFileController(@PathVariable("id") int id , @RequestParam("image") MultipartFile file
    ) throws IOException {
    
        String relativePath = this.path;
       
        ResponsePost post = this.postService.getPostById(id);
        String name = this.uploadFile.UploadFileByPath(relativePath, file);
        post.setImageName(name);
        ResponsePost result = this.postService.updatePost(post, id);
        return new ResponseEntity<>(new ApiResponse<>(200, result, "successfully uploaded image"), HttpStatus.OK);
    }
    
}
