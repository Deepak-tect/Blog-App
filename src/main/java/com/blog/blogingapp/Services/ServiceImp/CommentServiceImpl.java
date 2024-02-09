package com.blog.blogingapp.Services.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogingapp.Entities.Comment;
import com.blog.blogingapp.Entities.Post;
import com.blog.blogingapp.Entities.User;
import com.blog.blogingapp.Execptions.ResourceNotFoundExecption;
import com.blog.blogingapp.Payloads.ResponseComment;
import com.blog.blogingapp.Payloads.ResponsePost;
import com.blog.blogingapp.Payloads.ResponseUser;
import com.blog.blogingapp.Repositories.CommentRepo;
import com.blog.blogingapp.Repositories.PostRepo;
import com.blog.blogingapp.Repositories.UserRepo;
import com.blog.blogingapp.Services.CommentService;;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseComment createCommment(ResponseComment responseComment, int uid , int pid) {
        Optional<User> optionUser = this.userRepo.findById(uid);
        Optional<Post> optionPost = this.postRepo.findById(pid);
        if(!optionUser.isPresent() || !optionPost.isPresent()){
            throw new ResourceNotFoundExecption("pid or uid not found", "id", pid);
        }
        responseComment.setPost(this.modelMapper.map(optionPost.get(), ResponsePost.class));
        responseComment.setUser(this.modelMapper.map(optionUser.get(), ResponseUser.class));
        Comment newComment = this.commentRepo.save(this.modelMapper.map(responseComment, Comment.class));
        return this.modelMapper.map(newComment, ResponseComment.class);
    }

    @Override
    public ResponseComment updateComment(ResponseComment responseComment, int id) {
        Optional<Comment> option = this.commentRepo.findById(id);
        if(option.isPresent()){
            Comment comment = option.get();
            comment.setContent(responseComment.getContent());
            Comment updatedComment = this.commentRepo.save(comment);
            return this.modelMapper.map(updatedComment, ResponseComment.class);
        }
        throw new ResourceNotFoundExecption("Comment", "id", id);
    }

    @Override
    public ResponseComment getCommentId(int id) {
        Optional<Comment> option = this.commentRepo.findById(id);
        if(option.isPresent()){
            Comment updatedComment = option.get();
            return this.modelMapper.map(updatedComment, ResponseComment.class);
        }
        throw new ResourceNotFoundExecption("Comment", "id", id);
    }

    @Override
    public List<ResponseComment> getAllComment() {
        List<Comment> comments = this.commentRepo.findAll();
        List<ResponseComment> responseComment = new ArrayList<>();
        for(Comment comment : comments){
            responseComment.add(this.modelMapper.map(comment, ResponseComment.class));
        }
        return responseComment;
    }

    @Override
    public List<ResponseComment> getCommentOfUser(int id) {
        Optional<User> user = this.userRepo.findById(id);
        if(user.isPresent()){
            List<Comment> comments = this.commentRepo.findByUser(user.get());
            List<ResponseComment> responseComment = new ArrayList<>();
            for(Comment comment : comments){
                responseComment.add(this.modelMapper.map(comment, ResponseComment.class));
            }
            return responseComment;
        }
        throw new ResourceNotFoundExecption("Comment", "id", id);
    }

    @Override
    public List<ResponseComment> getCommnetOfPost(int id) {
        Optional<Post> post = this.postRepo.findById(id);
        if(post.isPresent()){
            List<Comment> comments = this.commentRepo.findByPost(post.get());
            List<ResponseComment> responseComment = new ArrayList<>();
            for(Comment comment : comments){
                responseComment.add(this.modelMapper.map(comment, ResponseComment.class));
            }
            return responseComment;
        }
        throw new ResourceNotFoundExecption("Comment", "id", id);
    }
    
}
