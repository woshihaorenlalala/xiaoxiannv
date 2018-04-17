package com.play.base.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/2/23.
 */
@Component
@Order(value = 2)
public class MyStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("》》》》》》》》》》服务启动执行，执行加载数据等操作1111111《《《《《《《《《《");
    }
}
