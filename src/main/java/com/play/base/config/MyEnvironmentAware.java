package com.play.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Administrator on 2018/2/23.
 */
@Configuration
public class MyEnvironmentAware implements EnvironmentAware {

    //注入application.properties的url
    @Value("${spring.datasource.url}")
    private String myUrl;

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(environment.getProperty("spring.datasource.url"));
    }
}
