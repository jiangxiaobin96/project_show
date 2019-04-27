package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.impl.HomeDaoImpl;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.entity.Home;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;


@RestController
public class LoginController {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private HomeDaoImpl homeDao;

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

    @RequestMapping(value = "/home_page",method = RequestMethod.POST)
    public List<Home> getHomePage(int authority){
        System.out.println(authority);
        List<Home> list = homeDao.getHomePage();
        System.out.println(list);
        Iterator<Home> it = list.iterator();
        if(authority == 0) {
            while(it.hasNext()){
                String tip = it.next().getTitle();
                if(tip.equals("管理员") || tip.equals("老师")){
                    it.remove();
                }
            }
            System.out.println(list.size());
            return list;
        }
        if(authority == 2) {
            while(it.hasNext()){
                String tip = it.next().getTitle();
                if(tip.equals("管理员")){
                    it.remove();
                }
            }
            System.out.println(list.size());
            return list;
        }
        if(authority == 1) {
            while(it.hasNext()){
                String tip = it.next().getTitle();
                if(tip.equals("老师")){
                    it.remove();
                }
            }
            System.out.println(list.size());
            return list;
        }
        return list;
    }

    @RequestMapping("/layout")
    public String layout(){
        return "/";
    }
}
