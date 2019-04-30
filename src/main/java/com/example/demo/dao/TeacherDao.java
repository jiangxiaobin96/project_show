package com.example.demo.dao;

import com.example.demo.entity.Project;
import com.example.demo.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    int add(Teacher teacher);
    int deleteByName(String teacherName);
    int update(Teacher teacher);
    int teacherCount();
    int queryTeacherIdByName(String teacherName);
    List<Teacher> queryTeacherResourceByName(String teacherName);
    Teacher queryTeacherByName(String teacherName);
    List<String> queryTeacherNameList();
    List<Teacher> queryTeacherList(int index);
}
