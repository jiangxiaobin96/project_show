package com.example.demo.service;

import com.example.demo.entity.Video;

import java.util.List;

public interface VideoService {
    void VideoAdd(Video video, String projectName);
    List<Video> queryVideoByProjectName(String projectName);
}
