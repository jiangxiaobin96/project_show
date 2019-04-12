package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into user(name,password,authority) values(?,?,2)",user.getUserName(),user.getPassword());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update user set password=? where name=?",user.getPassword(),user.getUserName());
    }

    @Override
    public int deleteByName(String userName) {
        return jdbcTemplate.update("delete from user where name=?",userName);
    }

    @Override
    public User queryUserResourceByName(String userName) {
        System.out.println("user:"+userName);
//        String sql = "select * from user where name=" + "'"+ userName + "'";
        String sql = "select * from user where name=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,userName);
        System.out.println("database:"+user.getPassword());
        if(null != user){
            return user;
        }else {
            return null;
        }
    }
}
