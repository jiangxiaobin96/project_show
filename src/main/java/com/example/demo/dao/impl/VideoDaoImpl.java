package com.example.demo.dao.impl;

import com.example.demo.dao.VideoDao;
import com.example.demo.entity.Picture;
import com.example.demo.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoDaoImpl implements VideoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
//    private ProjectDaoImpl projectDao;

    @Override
    public int add(Video video) {
//        String sql = "select projectId from project where projectName=" + "'" + projectName + "'";
//        int projectId = jdbcTemplate.queryForObject(sql,Integer.class);
        return jdbcTemplate.update("insert into video(name,projectId) values(?,?)",video.getName(),video.getProjectId());
    }

    @Override
    public int deleteVideoByName(String videoName) {
        return jdbcTemplate.update("delete from video where name=?", videoName);
    }

    @Override
    public int deleteVideoByProjectId(int projectId) {
        return jdbcTemplate.update("delete from video where projectId=?",projectId);
    }

    @Override
    public List<Video> queryVideoByProjectId(int projectId) {
//        int projectId = projectDao.queryProjectIdByName(projectName);
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
