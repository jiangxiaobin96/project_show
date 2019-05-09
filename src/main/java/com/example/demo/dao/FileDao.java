package com.example.demo.dao;

import com.example.demo.entity.File;

import java.util.List;

public interface FileDao {
    int add(File file);
    int deleteFileByName(String fileName);
    int deleteFileByProjectId(int projectId);
    List<File> queryFileByProjectId(int projectId);
    String getUidName(String name);
}
