package com.example.demo.dao;

import com.example.demo.entity.Video;

import java.util.List;

public interface VideoDao {
    int add(Video video);
    int deleteVideoByName(String videoName);
    List<Video> queryVideoByProjectId(int projectId);
}
