package com.example.demo.dao;

import com.example.demo.entity.Picture;

import java.util.List;

public interface PictureDao {
    int add(Picture picture);
    int deletePictureByName(String pictureName);
    List<Picture> queryPictureByProjectId(int projectId);
}
