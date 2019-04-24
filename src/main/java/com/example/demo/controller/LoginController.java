package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserDaoImpl userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user){
        String userName = user.getUserName();
        String password = user.getPassword();
        System.out.println("userName"+userName);
        System.out.println("password"+password);
        User user1 = userDao.queryUserResourceByName(userName);
        if(password.equals(user1.getPassword())){
            return user1;
        }else{
            return null;
        }
    }

    @RequestMapping("/layout")
    public String layout(){
        return "/";
    }
}
