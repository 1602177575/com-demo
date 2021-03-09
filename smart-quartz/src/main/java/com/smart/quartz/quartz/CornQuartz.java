package com.smart.quartz.quartz;


import com.smart.quartz.jod.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

//cron 定时器
public class CornQuartz {

    public static void main(String[] args) throws SchedulerException {
        // quartz API
        // 调度器
        Scheduler scheduler2 = StdSchedulerFactory.getDefaultScheduler();


        //触发器
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ? "))
                .build();


        //jobDetail
        JobDetail build = JobBuilder.newJob(HelloJob.class).withIdentity("job2", "group2").build();

        //将 job 和触发器增加到调度器中
        scheduler2.scheduleJob(build,trigger);
        //开启
        scheduler2.start();
    }
}
