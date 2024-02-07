package com.blog.blogingapp.Services.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogingapp.Entities.Category;
import com.blog.blogingapp.Entities.Post;
import com.blog.blogingapp.Entities.User;
import com.blog.blogingapp.Execptions.ResourceNotFoundExecption;
import com.blog.blogingapp.Payloads.ResponsePost;
import com.blog.blogingapp.Repositories.CategoryRepo;
import com.blog.blogingapp.Repositories.PostRepo;
import com.blog.blogingapp.Repositories.UserRepo;
import com.blog.blogingapp.Services.PostService;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponsePost createPost(ResponsePost responsePost, int userId , int categoryId) {
        Optional<User> optionalUser =  this.userRepo.findById(userId);
        Optional<Category> optionalCategory =  this.categoryRepo.findById(categoryId);
        if(optionalCategory.isPresent() && optionalUser.isPresent()){
            Post post = this.modelMapper.map(responsePost, Post.class);
            post.setUser(optionalUser.get());
            post.setCategory(optionalCategory.get());
            postRepo.save(post);
            return this.modelMapper.map(post, ResponsePost.class);
        }
        throw new ResourceNotFoundExecption("post","id", categoryId);
        
    }

    @Override
    public ResponsePost updatePost(ResponsePost responsePost, int id) {
        Optional<Post> option = this.postRepo.findById(id);
        if(option.isPresent()){
            Post post = option.get();
            post.setContent(responsePost.getContent());
            post.setTitle(responsePost.getTitle());
            post.setImageName(responsePost.getImageName());
            postRepo.save(post);
            return this.modelMapper.map(post, ResponsePost.class);
        }
        throw new ResourceNotFoundExecption("post", "id", id);
    }

    @Override
    public void deletePost(int id) {
        Optional<Post> option = this.postRepo.findById(id);
        if(option.isPresent()){
            Post post = option.get();
            postRepo.delete(post);
            return;
        }
        throw new ResourceNotFoundExecption("post", "id", id);

    }

    @Override
    public List<ResponsePost> getAllPost() {
        List<Post> getPosts = postRepo.findAll();
        List<ResponsePost> result = new ArrayList<ResponsePost>();
        for(Post post : getPosts){
            result.add(this.modelMapper.map(post , ResponsePost.class));
        }
        return result;
    }

    @Override
    public ResponsePost getPostById(int id) {
        Optional<Post> optional = postRepo.findById(id);
        if(optional.isPresent()){
            return modelMapper.map(optional.get(), ResponsePost.class);
        }
        throw new ResourceNotFoundExecption("post", "id", id);
    }

    @Override
    public List<ResponsePost> getPostOfUser(int id) {
        Optional<User> optional = userRepo.findById(id);
        if(optional.isPresent()){
            List<Post> posts = this.postRepo.findByUser(optional.get());
            List<ResponsePost> result = new ArrayList<ResponsePost>();
            for(Post post : posts){
                result.add(this.modelMapper.map(post, ResponsePost.class));
            }
            return result;
        }
        throw new ResourceNotFoundExecption("post", "id", id);
        
    }

    @Override
    public List<ResponsePost> getPostOfCategory(int id) {
        Optional<Category> optional = categoryRepo.findById(id);
        if(optional.isPresent()){
            List<Post> posts = this.postRepo.findByCategory(optional.get());
            List<ResponsePost> result = new ArrayList<ResponsePost>();
            for(Post post : posts){
                result.add(this.modelMapper.map(post, ResponsePost.class));
            }
            return result;
        }
        throw new ResourceNotFoundExecption("post", "id", id);
    }
    
}
