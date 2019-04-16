package com.example.demo.dao;

import com.example.demo.entity.Video;

import java.util.List;

public interface VideoDao {
    int add(Video video);
    int deleteVideoByName(String videoName);
    int deleteVideoByProjectId(int projectId);
    List<Video> queryVideoByProjectId(int projectId);
}
