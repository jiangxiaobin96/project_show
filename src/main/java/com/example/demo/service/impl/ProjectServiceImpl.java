package com.example.demo.service.impl;

import com.example.demo.dao.impl.FileDaoImpl;
import com.example.demo.dao.impl.PictureDaoImpl;
import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.dao.impl.VideoDaoImpl;
import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDaoImpl projectDao;
    @Autowired
    private FileDaoImpl fileDao;
    @Autowired
    private PictureDaoImpl pictureDao;
    @Autowired
    private VideoDaoImpl videoDao;

    @Override
    public void delProjectByName(String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        fileDao.deleteFileByProjectId(projectId);
        pictureDao.deletePictureByProjectId(projectId);
        videoDao.deleteVideoByProjectId(projectId);
        projectDao.deleteByProjectName(projectName);
    }

    @Override
    public void typeAndfinishDateAdd(Project project) {
        String typeName = project.getType();
        String finishDate = project.getFinishDate();
        List<String> typeList = projectDao.queryTypeList();
        List<String> finishList = projectDao.queryFinishDateList();
        if(typeList.contains(typeName)){

        }else{
            projectDao.typeAdd(typeName);
        }
        if(finishList.contains(finishDate)){

        }else{
            projectDao.finishAdd(finishDate);
        }
    }
}
