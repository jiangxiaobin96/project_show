package com.example.demo.service;

import com.example.demo.entity.Teacher;

public interface TeacherService {
    void addTeacher(Teacher teacher);
    void delTeacher(String teacherName);
}
