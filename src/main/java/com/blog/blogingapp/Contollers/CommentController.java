package com.blog.blogingapp.Contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogingapp.Payloads.ResponseComment;
import com.blog.blogingapp.Services.CommentService;
import com.blog.blogingapp.Utils.ApiResponse;



@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{uid}/post/{pid}/comment/")
    public ResponseEntity<ApiResponse<ResponseComment>> createCommentController(@RequestBody ResponseComment entity,
    @PathVariable int uid , @PathVariable int pid) {
        ResponseComment responseComment = this.commentService.createCommment(entity, uid ,pid);
        return new ResponseEntity<>(new ApiResponse<>(201, responseComment, "Successfully added comment"),HttpStatus.CREATED);
    }
    

    @PostMapping("/comment/{id}")
    public ResponseEntity<ApiResponse<ResponseComment>> updateCommentController(@RequestBody ResponseComment entity, @PathVariable int id) {
        ResponseComment responseComment = this.commentService.updateComment(entity,id);
        return new ResponseEntity<>(new ApiResponse<>(200, responseComment, "Successfully updated comment"),HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<ApiResponse<ResponseComment>> getCommentByIdController(@PathVariable("id") int id) {
        ResponseComment responseComment = this.commentService.getCommentId(id);
        return new ResponseEntity<>(new ApiResponse<>(200, responseComment, "Successfully fetched comment"),HttpStatus.CREATED);
    }

    @GetMapping("/comment/")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> getCommentController() {
        List<ResponseComment> responseComment = this.commentService.getAllComment();
        return new ResponseEntity<>(new ApiResponse<>(200, responseComment, "Successfully fetched comments"),HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}/comment/")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> getCommentByUserController(@PathVariable int id) {
        List<ResponseComment> responseComment = this.commentService.getCommentOfUser(id);
        return new ResponseEntity<>(new ApiResponse<>(200, responseComment, "Successfully fetched comments"),HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}/comment/")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> getCommentByPostController(@PathVariable int id) {
        List<ResponseComment> responseComment = this.commentService.getCommnetOfPost(id);
        return new ResponseEntity<>(new ApiResponse<>(200, responseComment, "Successfully fetched comments"),HttpStatus.CREATED);
    }
    
    
}
