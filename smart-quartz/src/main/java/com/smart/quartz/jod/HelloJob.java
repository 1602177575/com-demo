package com.smart.quartz.jod;
//jod 任务


import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("执行任务1");
        System.out.println(new Date());

        JobDetail jobDetail = context.getJobDetail();

        System.out.println(jobDetail.getKey().getName());
        System.out.println(jobDetail.getKey().getGroup());

    }
}
