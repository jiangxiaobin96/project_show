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
        String sql = "insert into project(projectName,typeId,finishDate,studentName,teacherId,projectDetail,teamDetail,firstPicture) values(?,?,?,?,?,?,?,?)";
        System.out.println(project.getProjectName());
        System.out.println(project.getType());
        System.out.println(project.getFinishDate());
        System.out.println(project.getStudentName());
        System.out.println(project.getTeacherName());
        System.out.println(project.getProjectDetail());
        System.out.println(project.getTeamDetail());
        return jdbcTemplate.update(sql,project.getProjectName(),project.getType(),project.getFinishDate(),project.getStudentName(),project.getTeacherName(),project.getProjectDetail(),project.getTeamDetail(),project.getFirstPicture());
    }

    @Override
    public int update(Project project) {
        String sql = "update project set typeId=?,finishDate=?,studentName=?,teacherId=?,projectDetail=?,teamDetail=?,firstPicture=? where projectName=?";
        return jdbcTemplate.update(sql,project.getType(),project.getFinishDate(),project.getStudentName(),project.getTeacherName(),project.getProjectDetail(),project.getTeamDetail(),project.getFirstPicture(),project.getProjectName());
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
    public String queryPorjectNameById(int projectId) {
        String sql = "select projectName from project where projectId=" + projectId;
        String projectName = jdbcTemplate.queryForObject(sql,String.class);
        return projectName;
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
    public List<String> queryProjectNameList() {
        String sql = "select projectName from project";
//        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        List<String> list = jdbcTemplate.queryForList(sql,String.class);
//        System.out.println("list:"+list);
        return list;
    }

    @Override
    public List<String> queryHomePageList() {
        String sql = "select tip from home_page";
//        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
        List<String> list = jdbcTemplate.queryForList(sql,String.class);
        return list;
    }

    @Override
    public int typeAdd(String typeName) {
        return jdbcTemplate.update("insert into type(typeName) value(?)",typeName);
    }

    @Override
    public int finishAdd(String finishDateName) {
        return jdbcTemplate.update("insert into finishDate(finishDateName) value(?)",finishDateName);
    }

    @Override
    public List<String> queryTypeList() {
        String sql = "select typeName from type";
        List<String> list = jdbcTemplate.queryForList(sql,String.class);
        return list;
    }

    @Override
    public List<String> queryFinishDateList() {
        String sql = "select finishDateName from finishDate";
        List<String> list = jdbcTemplate.queryForList(sql,String.class);
        return list;
    }

    @Override
    public List<Project> queryProjectList() {
        String sql = "select * from project";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        List<Project> projects = jdbcTemplate.query(sql,rowMapper);
        if(null != projects && projects.size() > 0){
            return projects;
        }else{
            return null;
        }
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

    @Override
    public List<Project> queryProjectByTeacherName(String teacherName) {
        String sql = "select * from project where teacherName=" + "'" + teacherName + "'";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        List<Project> projects = jdbcTemplate.query(sql,rowMapper);
        if(null != projects && projects.size() > 0){
            return projects;
        }else{
            return null;
        }
    }
}
