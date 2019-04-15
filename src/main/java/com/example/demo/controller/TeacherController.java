package com.example.demo.controller;

import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.dao.impl.TeacherDaoImpl;
import com.example.demo.entity.*;
import com.example.demo.service.impl.FileServiceImpl;
import com.example.demo.service.impl.PictureServiceImpl;
import com.example.demo.service.impl.VideoServiceImpl;
import com.fasterxml.jackson.databind.ser.std.FileSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private ProjectDaoImpl projectDao;
    @Autowired
    private TeacherDaoImpl teacherDao;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private PictureServiceImpl pictureService;
    @Autowired
    private VideoServiceImpl videoService;

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

//    public String teacherEdit(Teacher teacher){     //管理员编辑老师信息
//        teacherDao.update(teacher);
//        return "编辑成功";
//    }
//
//    public String deleteTeacher(String teacherName){        //管理员删除老师
//        teacherDao.deleteByName(teacherName);
//        return "删除成功";
//    }

    @RequestMapping(value = "/uploadProject", method = RequestMethod.POST)
    @ResponseBody
    public String uploadProject(Project project){   //老师project内容上传至数据库
        List<File> files = project.getFileList();
        List<Picture> pictures = project.getPictureList();
        List<Video> videos = project.getVideoList();
        for(File file : files){
            fileService.FileAdd(file,project.getProjectName());
        }
        for(Picture picture : pictures){
            pictureService.PictureAdd(picture,project.getProjectName());
        }
        for(Video video : videos){
            videoService.VideoAdd(video,project.getProjectName());
        }
//        String projectName = project.getProjectName();
//        int typeId = project.getTypeId();
//        String finishDate = project.getFinishDate();
//        String studentName = project.getStudentName();
//        int teacherId = project.getTeacherId();
//        String projectDetail = project.getProjectDetail();
//        String teamDetail = project.getTeamDetail();
        projectDao.add(project);
        return "上传成功";
    }
}
