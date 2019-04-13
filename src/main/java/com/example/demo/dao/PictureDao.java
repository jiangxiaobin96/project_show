package com.example.demo.dao;

import com.example.demo.entity.Picture;

import java.util.List;

public interface PictureDao {
    int add(Picture picture, String projectName);
    int deletePictureByName(String pictureName);
    List<Picture> queryPictureByProjectName(String projectName);
}
