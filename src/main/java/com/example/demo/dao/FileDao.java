package com.example.demo.dao;

import com.example.demo.entity.File;

import java.util.List;

public interface FileDao {
    int add(File file,String projectName);
    int deleteFileByName(String fileName);
    List<File> queryFileByProjectName(String projectName);
}
