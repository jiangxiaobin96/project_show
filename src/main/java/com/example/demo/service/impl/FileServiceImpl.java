package com.example.demo.service.impl;

import com.example.demo.dao.impl.FileDaoImpl;
import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.entity.File;
import com.example.demo.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private ProjectDaoImpl projectDao;
    @Resource
    private FileDaoImpl fileDao;

    @Override
    public void FileAdd(File file, String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        file.setProjectId(projectId);
        fileDao.add(file);
    }

    @Override
    public void queryFileByProjectName(String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        fileDao.queryFileByProjectId(projectId);
    }
}
