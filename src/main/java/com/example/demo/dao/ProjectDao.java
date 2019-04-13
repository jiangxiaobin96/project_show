package com.example.demo.dao;

import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectDao {
    int add(Project project);
    int update(Project project);
    int updateRating(String projectName);
    int deleteByProjectName(String projectName);
    int queryProjectIdByName(String projectName);
    Project queryProjectResourceByName(String projectName);
    List<String> queryProjectList();
    List<Project> queryProjectListByType(int type);
    List<Project> queryProjectListByTime(String finishDate);
}
