package com.example.demo.service;

import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectService {
//    void uploadProject();
    void delProjectById(int projectId);
    void typeAndfinishDateAdd(Project project);
}
