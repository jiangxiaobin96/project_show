package com.example.demo.dao;

import com.example.demo.entity.Video;

import java.util.List;

public interface VideoDao {
    int add(Video video, String projectName);
    int deleteVideoByName(String videoName);
    List<Video> queryVideoByProjectName(String projectName);
}
