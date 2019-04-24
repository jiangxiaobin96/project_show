package com.example.demo.dao.impl;

import com.example.demo.dao.PictureDao;
import com.example.demo.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PictureDaoImpl implements PictureDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
//    private ProjectDaoImpl projectDao;

    @Override
    public int add(Picture picture) {
//        String sql = "select projectId from project where projectName=" + "'" + projectName + "'";
//        int projectId = jdbcTemplate.queryForObject(sql,Integer.class);
        return jdbcTemplate.update("insert into picture(pictureName,projectId) values(?,?)",picture.getName(),picture.getProjectId());
    }

    @Override
    public int deletePictureByName(String pictureName) {
        return jdbcTemplate.update("delete from project where pictureName=?", pictureName);
    }

    @Override
    public int deletePictureByProjectId(int projectId) {
        return jdbcTemplate.update("delete from picture where projectId=?",projectId);
    }

    @Override
    public List<Picture> queryPictureByProjectId(int projectId) {
//        int projectId = projectDao.queryProjectIdByName(projectName);
        RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<>(Picture.class);
        String sql2 = "select * from video where projectId=" + projectId;
        List<Picture> pictureList = jdbcTemplate.query(sql2,rowMapper);
        if(null != pictureList && pictureList.size() > 0){
            return pictureList;
        }else{
            return null;
        }
    }
}
