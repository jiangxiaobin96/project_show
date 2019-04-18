package com.example.demo.service.impl;

import com.example.demo.dao.impl.PictureDaoImpl;
import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.entity.Picture;
import com.example.demo.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Resource
    private ProjectDaoImpl projectDao;
    @Resource
    private PictureDaoImpl pictureDao;

    @Override
    public void PictureAdd(Picture picture, String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        picture.setProjectId(projectId);
        pictureDao.add(picture);
    }

    @Override
    public List<Picture> queryPictureByProjectName(String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        List<Picture> list = pictureDao.queryPictureByProjectId(projectId);
        return list;
    }
}
