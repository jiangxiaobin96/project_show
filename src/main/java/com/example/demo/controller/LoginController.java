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
    @ResponseBody
    public String login(User user){
        String userName = user.getUserName();
        String password = user.getPassword();
        System.out.println(userName);
        System.out.println(password);
        if(password.equals(userDao.queryUserResourceByName(userName).getPassword())){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/layout")
    public String layout(){
        return "/";
    }
}
