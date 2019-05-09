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
        String uidName = file.getSize() + "_" + file.getName();
        return jdbcTemplate.update("insert into file(name,uid,projectId) values(?,?,?)",file.getName(),uidName,file.getProjectId());
    }

    @Override
    public int deleteFileByName(String fileName) {
        return jdbcTemplate.update("delete from file where name=?", fileName);
    }

    @Override
    public int deleteFileByProjectId(int projectId) {
        return jdbcTemplate.update("delete from file where projectId=?",projectId);
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

    @Override
    public String getUidName(String name) {
        String sql = "select uid from file where name="+"'"+name+"'";
        String uidName = jdbcTemplate.queryForObject(sql,String.class);
        return uidName;
    }
}
