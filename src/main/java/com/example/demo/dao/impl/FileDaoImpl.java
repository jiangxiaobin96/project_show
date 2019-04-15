package com.example.demo.dao.impl;

import com.example.demo.dao.FileDao;
import com.example.demo.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDaoImpl implements FileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //private ProjectDaoImpl projectDao;

    @Override
    public int add(File file) {
//        String sql = "select projectId from project where projectName=" + "'" + projectName + "'";
//        int projectId = jdbcTemplate.queryForObject(sql,Integer.class);
        return jdbcTemplate.update("insert into file(fileName,fileUrl,projectId) values(?,?,?)",file.getFileName(),file.getFileUrl(),file.getProjectId());
    }

    @Override
    public int deleteFileByName(String fileName) {
        return jdbcTemplate.update("delete from file where fileName=?", fileName);
    }

    @Override
    public List<File> queryFileByProjectId(int projectId) {
//        String sql = "select projectId from project where projectName=" + "'" + projectName + "'";
//        int projectId = jdbcTemplate.queryForObject(sql,Integer.class);
//        int projectId = projectDao.queryProjectIdByName(projectName);
        RowMapper<File> rowMapper = new BeanPropertyRowMapper<>(File.class);
        String sql = "select * from file where projectId=" + projectId;
        List<File> fileList = jdbcTemplate.query(sql,rowMapper);
        if(null != fileList && fileList.size() > 0){
            return fileList;
        }else{
            return null;
        }
    }
}
