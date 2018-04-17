package com.play.base.config;

import com.play.base.interceptor.FileUploadInterceptor;
import com.play.base.interceptor.MyInterceptor;
import com.play.base.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2018/2/23.
 */
@Configuration
public class MyWebAppConfigure extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //多个拦截器组成一个拦截链
        //addPathPatterns 用于拦截规则
        //excludePathPatterns 用户排除拦截
        registry.addInterceptor(new FileUploadInterceptor()).addPathPatterns("/**"); //上传大小在application中配置了
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
