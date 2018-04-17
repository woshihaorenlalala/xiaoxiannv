package com.play.base.schedul;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by Administrator on 2018/2/6.
 */
@Configuration
@EnableScheduling
    public class ScheduledConfig {

    @Autowired
    private JavaMailSender javaMailSender;

    /*  "0 0 12 * * ?"    每天中午十二点触发
"0 15 10 ? * *"    每天早上10：15触发
"0 15 10 * * ?"    每天早上10：15触发
"0 15 10 * * ? *"    每天早上10：15触发
"0 15 10 * * ? 2005"    2005年的每天早上10：15触发
"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发*/
    @Scheduled(cron = "0 0-59 11 * * ?")   //每天10点5分-35分每分钟出发一次
    public void scheduler() throws GeneralSecurityException {

        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
    }
}
