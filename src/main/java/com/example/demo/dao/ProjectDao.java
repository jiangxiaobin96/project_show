package com.example.demo.dao;

import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectDao {
    int add(Project project);
    int update(Project project);
    int updateRating(String projectName);
    int deleteByProjectName(String projectName);
    int queryProjectIdByName(String projectName);
    String queryPorjectNameById(int projectId);
    Project queryProjectResourceByName(String projectName);
    List<String> queryProjectNameList();
    List<String> queryHomePageList();
    int typeAdd(String typeName);
    int finishAdd(String finishDateName);
    List<String> queryTypeList();
    List<String> queryFinishDateList();
    List<Project> queryProjectList();
    List<Project> queryProjectListByType(int type);
    List<Project> queryProjectListByTime(String finishDate);
    List<Project> queryProjectListByRating();
    List<Project> queryProjectByTeacherName(String teacherName);
}
