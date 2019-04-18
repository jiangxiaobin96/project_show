package com.example.demo.dao.impl;

import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.Project;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Teacher teacher) {
        String sql = "insert into teacher(teacherName,pictureUrl,working,address,introduction,workExperience) values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,teacher.getTeacherName(),teacher.getPictureUrl(),teacher.getWorking(),teacher.getAddress(),teacher.getIntroduction(),teacher.getWorkExperience());
    }

    @Override
    public int deleteByName(String teacherName) {
        return jdbcTemplate.update("delete from teacher where teacherName=?",teacherName);
    }

    @Override
    public int update(Teacher teacher) {
        String sql = "update teacher set pictureUrl=?,working=?,address=?,introduction=?,workExperience=? where teacherName=?";
        return jdbcTemplate.update(sql,teacher.getPictureUrl(),teacher.getWorking(),teacher.getAddress(),teacher.getIntroduction(),teacher.getWorkExperience(),teacher.getTeacherName());
    }

    @Override
    public int queryTeacherIdByName(String teacherName) {
        String sql = "select * from teacher where teacherName=" + "'" + teacherName + "'";
        int teacherId = jdbcTemplate.queryForObject(sql,Integer.class);
        return teacherId;
    }

    @Override
    public Teacher queryTeacherByName(String teacherName) {
        String sql = "select * from teacher where teacherName=?";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        Teacher teacher = jdbcTemplate.queryForObject(sql,rowMapper,teacherName);
        if(null != teacher){
            return teacher;
        }else{
            return  null;
        }
    }

    @Override
    public List<String> queryTeacherNameList() {
        String sql = "select teacherName from teacher";
        System.out.println("sql:"+sql);
//        RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
//        List<String> list = jdbcTemplate.query(sql,rowMapper);
        List<String> list = jdbcTemplate.queryForList(sql,String.class);
        System.out.println(list);
        return list;
    }

    @Override
    public List<Teacher> queryTeacherList() {
        String sql = "select * from teacher";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teachers = jdbcTemplate.query(sql,rowMapper);
        if(null != teachers && teachers.size() > 0){
            return teachers;
        }else{
            return null;
        }
    }
}
