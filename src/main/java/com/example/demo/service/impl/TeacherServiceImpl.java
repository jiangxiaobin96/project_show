package com.example.demo.service.impl;

import com.example.demo.dao.impl.TeacherDaoImpl;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDaoImpl teacherDao;
    @Autowired
    private UserDaoImpl userDao;

    @Override
    public void addTeacher(Teacher teacher) {
        User user = new User();
        user.setUserName(teacher.getTeacherName());
        user.setPassword("123456");//默认密码
        userDao.add(user);
        int teacherId = userDao.queryUserIdByName(teacher.getTeacherName());
        teacher.setTeacherId(teacherId);
        teacherDao.add(teacher);
    }

    @Override
    public void delTeacher(String teacherName) {
        teacherDao.deleteByName(teacherName);
        userDao.deleteByName(teacherName);
    }
}
