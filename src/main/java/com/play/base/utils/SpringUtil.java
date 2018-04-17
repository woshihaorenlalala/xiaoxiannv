package com.play.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 普通类调用Spring bean对象：
 * Created by Administrator on 2018/2/9.
 */
@Component
public class SpringUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null){
            SpringUtil.applicationContext = applicationContext;
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------com.play.base.utils.SpringUtil------------------------------------------------------");
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取" +
                        "applicationContext对象,applicationContext="+SpringUtil.applicationContext+"========");
        System.out.println("---------------------------------------------------------------------");
    }
    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    //通过name获取Bean
    public static  Object getBean(String name){
        return applicationContext.getBean(name);
    }
    //通过class获取Bean
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
    //通过name和class 返回bean
    public static <T> T getBean(String name , Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }



}
