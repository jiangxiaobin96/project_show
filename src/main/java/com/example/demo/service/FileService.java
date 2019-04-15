package com.example.demo.service;

import com.example.demo.entity.File;

public interface FileService {
    void FileAdd(File file,String projectName);
    void queryFileByProjectName(String projectName);
    
}
