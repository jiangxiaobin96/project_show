package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebAppConfig {
    @Value("${file.location}")
    private String location;
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB，MB
        factory.setMaxFileSize("500MB");
        //设置总上传数据大小
        factory.setMaxRequestSize("1000MB");
        return factory.createMultipartConfig();
    }
}
