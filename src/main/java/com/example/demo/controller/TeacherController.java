package com.example.demo.controller;

import com.example.demo.dao.FileDao;
import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.dao.impl.TeacherDaoImpl;
import com.example.demo.entity.*;
import com.example.demo.service.impl.FileServiceImpl;
import com.example.demo.service.impl.PictureServiceImpl;
import com.example.demo.service.impl.ProjectServiceImpl;
import com.example.demo.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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
    @Autowired
    private ProjectServiceImpl projectService;

    @RequestMapping("/TeacherNameList")
    public List<String> getTeacherNameList(){       //向前端发送教师列表
        List<String> list = teacherDao.queryTeacherNameList();
        return list;
    }

    @RequestMapping("/TeacherList")
    public List<Teacher> getTeacherList(int index){
        List<Teacher> list = teacherDao.queryTeacherList(index);
        return list;
    }

    @RequestMapping(value = "/TeacherCount")
    public int getTeacherCount(){
        return teacherDao.teacherCount();
    }

    @RequestMapping(value = "/searchTeacherName", method = RequestMethod.POST)
    public Teacher getTeacherByName(String teacherName){        //前端通过老师名搜索老师
        return teacherDao.queryTeacherByName(teacherName);
    }

    @RequestMapping(value = "/searchTeacherByName",method = RequestMethod.POST)
    public List<Teacher> getTeacherListByName(String teacherName){
        return teacherDao.queryTeacherResourceByName(teacherName);
    }
//    public String uploadTeacher(Teacher teacher){       //上传老师信息到数据库
//        teacherDao.add(teacher);
//        return "上传成功";
//    }

    @RequestMapping(value = "/teacherShow", method = RequestMethod.POST)
    public Teacher getTeacher(String tName){       //将老师信息传给前端
        System.out.println(tName);
        Teacher teacher = teacherDao.queryTeacherByName(tName);
        System.out.println(teacher.getWorkExperience());
        List<String> list = Arrays.asList(teacher.getWorkExperience().split(","));
        System.out.println(list);
        teacher.setWorkList(list);
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
    public String uploadProject(@RequestBody Project project){   //老师project内容上传至数据库
        System.out.println("success");
        projectService.typeAndfinishDateAdd(project);
        projectDao.add(project);
//        List<MultipartFile> fileList = project.getFileList();

        List<File> files = project.getFileList();
        List<Picture> pictures = project.getPictureList();
        System.out.println(pictures.size());
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
//        System.out.println(project.getProjectName());
//        System.out.println(project.getTypeId());
//        System.out.println(project.getFinishDate());
//        System.out.println(project.getStudentName());
//        System.out.println(project.getTeacherId());
//        System.out.println(project.getProjectDetail());
//        System.out.println(project.getTeamDetail());
//        System.out.println(files);
        return "上传成功";
    }
}
