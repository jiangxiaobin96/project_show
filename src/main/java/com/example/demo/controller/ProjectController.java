package com.example.demo.controller;

import com.example.demo.dao.impl.FileDaoImpl;
import com.example.demo.dao.impl.PictureDaoImpl;
import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.dao.impl.VideoDaoImpl;
import com.example.demo.entity.File;
import com.example.demo.entity.Picture;
import com.example.demo.entity.Project;
import com.example.demo.entity.Video;
import com.example.demo.service.PictureService;
import com.example.demo.service.impl.FileServiceImpl;
import com.example.demo.service.impl.PictureServiceImpl;
import com.example.demo.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectDaoImpl projectDao;
    @Autowired
    private FileDaoImpl fileDao;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private PictureServiceImpl pictureService;
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private PictureDaoImpl pictureDao;
    @Autowired
    private VideoDaoImpl videoDao;

    @RequestMapping("/projectList")
    public List<String> getProjectList(){      //向前端传项目列表
        List<String> projectNameList = projectDao.queryProjectList();
        return projectNameList;
    }

    public Project getProjectByName(String projectName){         //根据项目名称搜索
        return projectDao.queryProjectResourceByName(projectName);
    }

    public List<Project> getProjectListByType(int typeId){      //根据项目类型搜索
        List<Project> projectList = projectDao.queryProjectListByType(typeId);
        return projectList;
    }

    public List<Project> getProjectListByTime(String finishDate){       //根据项目时间搜索
        List<Project> projectList = projectDao.queryProjectListByTime(finishDate);
        return projectList;
    }

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

    @RequestMapping(value = "/projectShow")
    @ResponseBody
    public Project ProjectShow(String pName){   //    项目展示，将project的数据传到前端
        Project project = projectDao.queryProjectResourceByName(pName);
//        String projectName = projectDao.queryProjectResourceByName(pName).getProjectName();
//        int projectType = projectDao.queryProjectResourceByName(pName).getTypeId();
//        String finishDate = projectDao.queryProjectResourceByName(pName).getFinishDate();
//        String studentName = projectDao.queryProjectResourceByName(pName).getStudentName();
//        int teacherId = projectDao.queryProjectResourceByName(pName).getTeacherId();
//        String projectDetail = projectDao.queryProjectResourceByName(pName).getProjectDetail();
//        String teamDetail = projectDao.queryProjectResourceByName(pName).getTeamDetail();
//        //int projectId = projectDao.queryProjectIdByName(pName);
//        List<File> fileList = fileDao.queryFileByProjectName(pName);
//        Project project = new Project();
//        project.setProjectName(projectName);
//        project.setTypeId(projectType);
//        project.setFinishDate(finishDate);
//        project.setStudentName(studentName);
//        project.setTypeId(teacherId);
//        project.setProjectDetail(projectDetail);
//        project.setTeamDetail(teamDetail);
//        project.setFileList(fileList);
        return project;
    }

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

    public void projectRatingAdd(String projectName){       //打分++
        projectDao.updateRating(projectName);
    }

}
