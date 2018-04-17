package com.play.base.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/2/23.
 */
@Component
@Order(value = 1)
public class MyStartupRunner2 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("args:"+Arrays.asList(strings));   //这里的strings是application中main方法的args
        System.out.println("》》》》》》》》》》服务启动执行，执行加载数据等操作22222222《《《《《《《《《《");
    }
}
