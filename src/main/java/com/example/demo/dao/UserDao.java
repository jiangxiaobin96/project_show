package com.example.demo.dao;

import com.example.demo.entity.User;

public interface UserDao {
    int add(User user);
    int update(User user);
    int deleteByName(String userName);
    User queryUserResourceByName(String userName);
}
