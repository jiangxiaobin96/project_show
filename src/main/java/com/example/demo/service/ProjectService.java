package com.example.demo.service;

import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectService {
//    void uploadProject();
    void delProjectByName(String projectName);
    void typeAndfinishDateAdd(Project project);
}
