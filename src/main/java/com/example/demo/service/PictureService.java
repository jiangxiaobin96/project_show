package com.example.demo.service;


import com.example.demo.entity.Picture;

public interface PictureService {
    void PictureAdd(Picture picture, String projectName);
    void queryPictureByProjectName(String projectName);
}
