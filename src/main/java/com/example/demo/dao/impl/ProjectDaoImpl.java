package com.example.demo.dao.impl;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Project project) {
        String sql = "insert into project(projectName,typeId,finishDate,studentName,teacherId,projectDetail,teamDetail) values(?,?,?,?,?,?,?)";
        System.out.println(project.getProjectName());
        System.out.println(project.getTypeId());
        System.out.println(project.getFinishDate());
        System.out.println(project.getStudentName());
        System.out.println(project.getTeacherId());
        System.out.println(project.getProjectDetail());
        System.out.println(project.getTeamDetail());
        return jdbcTemplate.update(sql,project.getProjectName(),project.getTypeId(),project.getFinishDate(),project.getStudentName(),project.getTeacherId(),project.getProjectDetail(),project.getTeamDetail());
    }

    @Override
    public int update(Project project) {
        String sql = "update project set typeId=?,finishDate=?,studentName=?,teacherId=?,projectDetail=?,teamDetail=? where projectName=?";
        return jdbcTemplate.update(sql,project.getTypeId(),project.getFinishDate(),project.getStudentName(),project.getTeacherId(),project.getProjectDetail(),project.getTeamDetail(),project.getProjectName());
    }

    @Override
    public int updateRating(String projectName) {
        String sql = "select rating from project where projectName="+"'"+projectName+"'";
        System.out.println("sql:"+sql);
        int rating = jdbcTemplate.queryForObject(sql,Integer.class) + 1;
        return jdbcTemplate.update("update project set rating=? where projectName=?",rating,projectName);
    }

    @Override
    public int deleteByProjectName(String projectName) {
        return jdbcTemplate.update("delete from project where projectName=?",projectName);
    }

    @Override
    public int queryProjectIdByName(String projectName) {
        String sql = "select projectId from project where projectName="+"'"+projectName+"'";
        System.out.println("sql:"+sql);
        int projectId = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println("projectId"+projectId);
        return projectId;
    }


    @Override
    public Project queryProjectResourceByName(String projectName) {
        String sql = "select * from project where projectName=" + "'" + projectName + "'";
//        System.out.println("sql:"+sql);
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        List<Project> project = jdbcTemplate.query(sql,rowMapper);
//        System.out.println(project);
        if(null != project && project.size() > 0){
            System.out.println(project.get(0));
            return project.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<String> queryProjectList() {
        String sql = "select projectName from project";
//        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        List<String> list = jdbcTemplate.queryForList(sql,String.class);
//        System.out.println("list:"+list);
        return list;
    }

    @Override
    public List<String> queryHomePageList() {
        String sql = "select tip from home_page";
        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        List<String> list = jdbcTemplate.query(sql,rowMapper);
        return list;
    }

    @Override
    public List<Project> queryProjectListByType(int type) {
        String sql = "select * from project where typeId=" + type;
        System.out.println(sql);
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        List<Project> projects = jdbcTemplate.query(sql,rowMapper);
        if(null != projects && projects.size() > 0){
            return projects;
        }else{
            return null;
        }
    }

    @Override
    public List<Project> queryProjectListByTime(String finishDate) {
        String sql = "select * from project where finishDate=" + "'" + finishDate + "'";
        System.out.println(sql);
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        List<Project> projects = jdbcTemplate.query(sql,rowMapper);
        if(null != projects && projects.size() > 0){
            return projects;
        }else{
            return null;
        }
    }

    @Override
    public List<Project> queryProjectListByRating() {
        String sql = "select * from project order by rating desc limit 3";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        List<Project> projects = jdbcTemplate.query(sql,rowMapper);
        return projects;
    }
}
