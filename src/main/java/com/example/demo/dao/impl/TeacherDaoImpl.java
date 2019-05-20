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
        String sql = "insert into teacher(teacherId,teacherName,pictureUrl,uidName,working,address,introduction,workExperience) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,teacher.getTeacherId(),teacher.getTeacherName(),teacher.getPictureUrl(),teacher.getUidName(),teacher.getWorking(),teacher.getAddress(),teacher.getIntroduction(),teacher.getWorkExperience());
    }

    @Override
    public int deleteByName(String teacherName) {
        return jdbcTemplate.update("delete from teacher where teacherName=?",teacherName);
    }

    @Override
    public int update(Teacher teacher) {
        String sql = "update teacher set pictureUrl=?,uidName=?,working=?,address=?,introduction=?,workExperience=? where teacherName=?";
        return jdbcTemplate.update(sql,teacher.getPictureUrl(),teacher.getUidName(),teacher.getWorking(),teacher.getAddress(),teacher.getIntroduction(),teacher.getWorkExperience(),teacher.getTeacherName());
    }

    @Override
    public int teacherCount() {
        String sql = "select count(*) from teacher";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println("teacherCount:"+count);
        return count;
    }

    @Override
    public int queryTeacherIdByName(String teacherName) {
        String sql = "select * from teacher where teacherName=" + "'" + teacherName + "'";
        int teacherId = jdbcTemplate.queryForObject(sql,Integer.class);
        return teacherId;
    }

    @Override
    public String getUidName(String pictureName) {
        String sql = "select uidName from teacher where pictureUrl=" + "'" + pictureName + "'";
        System.out.println(sql);
//        String uidName = jdbcTemplate.queryForObject(sql,String.class);
        String uidName;
        try{
            uidName = jdbcTemplate.queryForObject(sql,String.class);
        }catch (Exception e){
            uidName = "fail";
        }

//        System.out.println("uidName:"+uidName);
        return uidName;
    }

    @Override
    public Teacher queryTeacherById(int teacherId) {
        String sql = "select * from teacher where teacherId=" + teacherId;
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teachers = jdbcTemplate.query(sql,rowMapper);
        if(null != teachers && teachers.size() > 0){
            return teachers.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<Teacher> queryTeacherResourceByName(String teacherName) {
        String sql = "select * from teacher where teacherName like" + "'%" + teacherName + "%'";
//        System.out.println("sql:"+sql);
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teacher = jdbcTemplate.query(sql,rowMapper);
//        System.out.println(project);
        if(null != teacher && teacher.size() > 0){
            //System.out.println(project.get(0));
            return teacher;
        }else{
            return null;
        }
    }

    @Override
    public Teacher queryTeacherByName(String teacherName) {
        String sql = "select * from teacher where teacherName=" + "'" + teacherName + "'";
        System.out.println("sql:"+sql);
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teachers = jdbcTemplate.query(sql,rowMapper);
        System.out.println(teachers.size());
//        System.out.println(project);
        if(null != teachers && teachers.size() > 0){
            //System.out.println(project.get(0));
            return teachers.get(0);
        }else{
            return null;
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
    public List<Teacher> queryTeacherList(int index) {
        String sql = "select * from teacher";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teachers = jdbcTemplate.query(sql,rowMapper);

        if(null != teachers && teachers.size() > 0){
            if (index > 0) {
                int fromIndex = (index-1)*9;
                int toIndex = index*9;
                if(toIndex > teachers.size()) toIndex = teachers.size();
                teachers = teachers.subList(fromIndex,toIndex);
            }
            return teachers;
        }else{
            return null;
        }
    }
}
