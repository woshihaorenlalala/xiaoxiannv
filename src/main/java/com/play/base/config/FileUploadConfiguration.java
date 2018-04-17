package com.play.base.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by Administrator on 2018/3/8.
 * 限制上传文件大小的一种方式
 */
//@Configuration
public class FileUploadConfiguration {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize("10MB");
        multipartConfigFactory.setMaxRequestSize("15MB");
        return multipartConfigFactory.createMultipartConfig();
    }
}
