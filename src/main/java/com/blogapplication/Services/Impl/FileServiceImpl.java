package com.blogapplication.Services.Impl;

import com.blogapplication.Services.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //Get File Name
        String originalFilename=file.getOriginalFilename();

        //Generate random name of the File
        String randomId= UUID.randomUUID().toString();
        String fileName=randomId.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));

        //Create full path
        String fullpath=path+ File.separator+fileName;

        //Create folder if not there
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //Copy the file content in the file
        Files.copy(file.getInputStream(), Paths.get(fullpath));
        return fileName;


    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException {
        //Get Full path of the file
        String fulpath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fulpath);
        return is;
    }
}
