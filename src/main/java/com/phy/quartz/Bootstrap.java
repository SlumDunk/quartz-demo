package com.phy.quartz;

import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Bootstrap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scheduler scheduler;
		try {
			// 获取Scheduler实例
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();

			// 具体任务
			JobDetail job = JobBuilder.newJob(HelloJob.class)
					.withIdentity("job1", "group1").build();

			// 触发时间点
			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
					.simpleSchedule().withIntervalInSeconds(5).repeatForever();

			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
					.cronSchedule("0 42 10 * * ? *");

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1").startNow()
					.withSchedule(simpleScheduleBuilder).build();

			Trigger cronTrigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group1").startNow()
					.withSchedule(cronScheduleBuilder).build();

			// 交由Scheduler安排触发
			scheduler.scheduleJob(job, trigger);
			scheduler.scheduleJob(job, cronTrigger);
			

			/* 为观察程序运行，此设置主程序睡眠3分钟才继续往下运行（因下一个步骤是“关闭Scheduler”） */
			try {
				TimeUnit.MINUTES.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
