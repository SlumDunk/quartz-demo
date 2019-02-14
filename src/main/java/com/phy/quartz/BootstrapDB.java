package com.phy.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapDB {
	private static Logger logger = LoggerFactory.getLogger(BootstrapDB.class);

	public static void main(String[] args) {
		try {
			// 获取Scheduler实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();

		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}
}
