package com.example.demo.dao.impl;

import com.example.demo.dao.HomeDao;
import com.example.demo.entity.Home;
import com.example.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HomeDaoImpl implements HomeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Home> getHomePage() {
        String sql = "select * from home_page";
        RowMapper<Home> rowMapper = new BeanPropertyRowMapper<>(Home.class);
        List<Home> homes = jdbcTemplate.query(sql,rowMapper);
        if(null != homes && homes.size() > 0){
            //System.out.println(project.get(0));
            return homes;
        }else{
            return null;
        }
    }
}
