package com.example.demo.service.impl;

import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.dao.impl.VideoDaoImpl;
import com.example.demo.entity.Video;
import com.example.demo.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private ProjectDaoImpl projectDao;
    @Resource
    private VideoDaoImpl videoDao;

    @Override
    public void VideoAdd(Video video, String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        video.setProjectId(projectId);
        videoDao.add(video);
    }

    @Override
    public List<Video> queryVideoByProjectName(String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        List<Video> list = videoDao.queryVideoByProjectId(projectId);
        return list;
    }
}
