package com.blog.blogingapp.Services;

import com.blog.blogingapp.Payloads.ResponseComment;
import java.util.*;
public interface CommentService {
    
    ResponseComment createCommment(ResponseComment responseComment, int uid , int pid);

    ResponseComment updateComment(ResponseComment responseComment , int id);

    ResponseComment getCommentId(int id);

    List<ResponseComment> getAllComment();

    List<ResponseComment> getCommentOfUser(int id);

    List<ResponseComment> getCommnetOfPost(int id);
}
