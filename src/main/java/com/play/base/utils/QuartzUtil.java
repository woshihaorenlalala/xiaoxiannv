package com.play.base.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by cc on 2018/6/1.
 */
public class QuartzUtil {
    public static void doJob(Class cls,String jobName,String cron ,String jobGroupName,String triggerName,String triggerGroup) throws Exception{
        //获取工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //获取任务调度器
        Scheduler scheduer = schedulerFactory.getScheduler();
        //创建一项作业
        JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName,jobGroupName).build();
        //创建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName,triggerGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).startNow().build();
        //告诉调度器使用触发器来调度任务
        scheduer.scheduleJob(jobDetail,cronTrigger);
        scheduer.start();
    }
}
