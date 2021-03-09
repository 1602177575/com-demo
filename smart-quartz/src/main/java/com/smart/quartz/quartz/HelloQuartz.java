package com.smart.quartz.quartz;


import cn.hutool.core.date.DateUtil;
import com.smart.quartz.jod.HelloJob;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.apache.catalina.Context;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.GregorianCalendar;

public class HelloQuartz {
    public static void main(String[] args) throws SchedulerException {
        // quartz API
        // 调度器
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();


        //触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                //什么时候开始  程序执行的时候
                .startNow()
                //执行几次  两面执行一次  一直触发
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                //什么时候结束
                .endAt(new GregorianCalendar(2021,3,1,22,15,30).getTime())
                .build();

        //jobDetail
        JobDetail build = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        //将 job 和触发器增加到调度器中
        scheduler.scheduleJob(build,trigger);
        //开启
        scheduler.start();
    }

}
