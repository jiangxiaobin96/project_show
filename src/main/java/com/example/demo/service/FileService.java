package com.example.demo.service;

import com.example.demo.entity.File;

import java.util.List;

public interface FileService {
    void FileAdd(File file,String projectName);
    List<File> queryFileByProjectId(int projectId);
}
