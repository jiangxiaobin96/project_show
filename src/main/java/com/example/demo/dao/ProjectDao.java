package com.example.demo.dao;

import com.example.demo.entity.Pagination;
import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectDao {
    int add(Project project);
    int update(Project project);
    int updateRating(int projectId);
    int deleteByProjectName(String projectName);
    int queryProjectIdByName(String projectName);
    int projectCount(Pagination pagination);
    String queryPorjectNameById(int projectId);
    List<Project> queryProjectResourceByName(String projectName);
    Project queryProjectByName(String projectName);
    Project queryProjectById(int projectId);
    List<String> queryProjectNameList();
    List<String> queryHomePageList();
    int typeAdd(String typeName);
    int finishAdd(String finishDateName);
    List<String> queryTypeList();
    List<String> queryFinishDateList();
    List<Project> queryProjectList(Pagination pagination);
    List<Project> queryProjectListByType(String typeName);
    List<Project> queryProjectListByTime(String finishDate);
    List<Project> queryProjectListByRating();
    List<Project> queryProjectByTeacherName(String teacherName);
}
