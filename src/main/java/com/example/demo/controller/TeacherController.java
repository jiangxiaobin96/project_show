package com.example.demo.controller;

import com.example.demo.dao.impl.TeacherDaoImpl;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherDaoImpl teacherDao;

    public List<String> getTeacherList(){       //向前端发送教师列表
        List<String> list = teacherDao.queryTeacherList();
        return list;
    }

    public Teacher getTeacherByName(String teacherName){        //前端通过老师名搜索老师
        return teacherDao.queryTeacherByName(teacherName);
    }

    public String uploadTeacher(Teacher teacher){       //上传老师信息到数据库
        teacherDao.add(teacher);
        return "上传成功";
    }

    public Teacher teacherShow(String TName){       //将老师信息传给前端
        Teacher teacher = teacherDao.queryTeacherByName(TName);
        return teacher;
    }

    public String teacherEdit(Teacher teacher){     //管理员编辑老师信息
        teacherDao.update(teacher);
        return "编辑成功";
    }
}
