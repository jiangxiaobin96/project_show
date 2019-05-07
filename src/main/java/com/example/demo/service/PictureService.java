package com.example.demo.service;


import com.example.demo.entity.Picture;

import java.util.List;

public interface PictureService {
    void PictureAdd(Picture picture, String projectName);
    List<Picture> queryPictureByProjectId(int projectId);
}
