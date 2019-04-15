package com.example.demo.service;

import com.example.demo.entity.Video;

public interface VideoService {
    void VideoAdd(Video video, String projectName);
    void queryVideoByProjectName(String projectName);
}
