package com.example.demo.dao.impl;

import com.example.demo.dao.VideoDao;
import com.example.demo.entity.Picture;
import com.example.demo.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class VideoDaoImpl implements VideoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ProjectDaoImpl projectDao;

    @Override
    public int add(Video video, String projectName) {
        String sql = "select projectId from project where projectName=" + "'" + projectName + "'";
        int projectId = jdbcTemplate.queryForObject(sql,Integer.class);
        return jdbcTemplate.update("insert into video(videoName,videoUrl,projectId) values(?,?,?)",video.getVideoName(),video.getVideoUrl(),projectId);
    }

    @Override
    public int deleteVideoByName(String videoName) {
        return jdbcTemplate.update("delete from video where videoName=?", videoName);
    }

    @Override
    public List<Video> queryVideoByProjectName(String projectName) {
        int projectId = projectDao.queryProjectIdByName(projectName);
        RowMapper<Video> rowMapper = new BeanPropertyRowMapper<>(Video.class);
        String sql2 = "select * from video where projectId=" + projectId;
        List<Video> videoList = jdbcTemplate.query(sql2,rowMapper);
        if(null != videoList && videoList.size() > 0){
            return videoList;
        }else{
            return null;
        }
    }
}