package com.example.demo.controller;

import com.example.demo.dao.FileDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.impl.*;
import com.example.demo.entity.*;
import com.example.demo.service.impl.FileServiceImpl;
import com.example.demo.service.impl.PictureServiceImpl;
import com.example.demo.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private ProjectDaoImpl projectDao;
    @Autowired
    private TeacherDaoImpl teacherDao;
    @Autowired
    private FileDaoImpl fileDao;
    @Autowired
    private PictureDaoImpl pictureDao;
    @Autowired
    private VideoDaoImpl videoDao;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private PictureServiceImpl pictureService;
    @Autowired
    private VideoServiceImpl videoService;

    @RequestMapping("/projectEdit")
    @ResponseBody
    public String projectEdit(Project project){     //管理员修改项目内容
        projectDao.update(project);
        return "编辑成功";
    }

    public String projectDel(String projectName){       //管理员删除项目
        projectDao.deleteByProjectName(projectName);
        return "删除成功";
    }

    public String projectFileAdd(Project project){      //管理员修改文件（上传）
        List<File> fileList = project.getFileList();
        for(File file : fileList){
            fileService.FileAdd(file,project.getProjectName());
        }
        return "文件上传成功";
    }

    public String projectFileDel(String fileName){      //管理员修改文件（删除）
        fileDao.deleteFileByName(fileName);
        return "文件删除成功";
    }

    public String projectPictureAdd(Project project){      //管理员修改图片（上传）
        List<Picture> pictureList = project.getPictureList();
        for(Picture picture : pictureList){
            pictureService.PictureAdd(picture,project.getProjectName());
        }
        return "文件上传成功";
    }

    public String projectPictureDel(String pictureName){      //管理员修改图片（删除）
        pictureDao.deletePictureByName(pictureName);
        return "文件删除成功";
    }

    public String projectVideoAdd(Project project){      //管理员修改视频（上传）
        List<Video> videoList = project.getVideoList();
        for(Video video : videoList){
            videoService.VideoAdd(video,project.getProjectName());
        }
        return "文件上传成功";
    }

    public String projectVideoDel(String videoName){      //管理员修改视频（删除）
        videoDao.deleteVideoByName(videoName);
        return "文件删除成功";
    }

    public String deleteUser(String userName){      //管理员删除用户
        userDao.deleteByName(userName);
        return "用户删除成功";
    }

    public String teacherEdit(Teacher teacher){     //管理员编辑老师信息
        teacherDao.update(teacher);
        return "编辑成功";
    }

    public String deleteTeacher(String teacherName){        //管理员删除老师
        teacherDao.deleteByName(teacherName);
        return "删除成功";
    }
}
