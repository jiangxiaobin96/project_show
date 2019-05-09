package com.example.demo.dao;

import com.example.demo.entity.Picture;

import java.util.List;

public interface PictureDao {
    int add(Picture picture);
    int deletePictureByName(String pictureName);
    int deletePictureByProjectId(int projectId);
    List<Picture> queryPictureByProjectId(int projectId);
    String getUidName(String name);
}
