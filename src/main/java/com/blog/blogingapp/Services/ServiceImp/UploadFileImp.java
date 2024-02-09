package com.blog.blogingapp.Services.ServiceImp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogingapp.Services.UploadFile;

@Service
public class UploadFileImp implements UploadFile {

    @Override
    public String UploadFileByPath(String path, MultipartFile file) throws IOException {
         // Validate file
         if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        // Sanitize file name
        String originalFilename = file.getOriginalFilename();
        
        // Generate unique filename
        String fileName = UUID.randomUUID().toString() + "_" + originalFilename;
        
        // Construct full file path
        // String filePath = Paths.get(path, fileName).toString();
        String filePath = path + File.separator + originalFilename;
        // Ensure directory exists
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // Copy file to destination
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        
        return fileName;
    }
    //     // File name
    //     String name = file.getOriginalFilename();

    //     // full path

    //     String filePath = path + File.separator + name;
    //     File f = new File(path);
    //     if(!f.exists()){
    //         f.mkdirs();
    //     }
    //     Files.copy(file.getInputStream(), Paths.get(filePath));
    //     return name;

    // }
    
}
