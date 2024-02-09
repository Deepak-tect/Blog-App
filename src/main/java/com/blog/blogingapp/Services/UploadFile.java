package com.blog.blogingapp.Services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
    String UploadFileByPath(String path , MultipartFile file) throws IOException;
}
