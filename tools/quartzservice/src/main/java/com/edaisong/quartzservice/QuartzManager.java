
package com.edaisong.quartzservice;

import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时任务管理类
 * @author hailongzhao
 * @date 20160223
 */
public class QuartzManager {
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

	/**
	 * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * @param jobName 任务名
	 * @param cls 任务
	 * @param time 时间设置，参考quartz说明文档
	 * @author hailongzhao
	 * @date 20160323
	 */
	public static void addJob(String jobName, Class cls, String time)
			throws Exception {
		addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, cls, time);
	}

	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @throws Exception
	 */
	public static void removeJob(String jobName) throws Exception {
		removeJob(jobName, JOB_GROUP_NAME);
	}

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @param jobClass
	 * @param time
	 * @throws Exception
	 */
	public static void addJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName, Class jobClass,
			String time) throws Exception {
		Scheduler sched = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		if (sched.checkExists(jobKey)) {
			modifyJobTime(jobName, jobGroupName, time);
		} else {
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(jobName, jobGroupName).build();// 任务名，任务组，任务执行类
			// 触发器
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(triggerName, triggerGroupName).startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(time))
					.build();
			sched.scheduleJob(jobDetail, trigger);
			startJobs();
		}
	}

/**
 * 获取一个任务的执行状态
 * @param jobName
 * @param jobGroupName
 * @return
 * @throws Exception
 */
	public static TriggerState checkJob(String jobName, String jobGroupName)
			throws Exception {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
		TriggerState state = scheduler.getTriggerState(triggerKey);
		return state;
	}

	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws Exception
	 */
	public static void removeJob(String jobName, String jobGroupName)
			throws Exception {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 暂停一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws Exception
	 */
	public static void pauseJob(String jobName, String jobGroupName)
			throws Exception {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws Exception
	 */
	public static void resumeJob(String jobName, String jobGroupName)
			throws Exception {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 立即运行任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @throws Exception
	 */
	public static void triggerJob(String jobName, String jobGroupName)
			throws Exception {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新任务（时间表达式）
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param time
	 * @throws Exception
	 */
	public static void modifyJobTime(String jobName, String jobGroupName,
			String time) throws Exception {
		Scheduler scheduler = gSchedulerFactory.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(time);

		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();

		// 按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 启动所有定时任务
	 * 
	 * @throws Exception
	 */
	public static void startJobs() throws Exception {
		Scheduler sched = gSchedulerFactory.getScheduler();
		if(!sched.isStarted()){
			sched.start();
		}
	}

	/**
	 * 关闭所有定时任务
	 * 
	 * @throws Exception
	 */
	public static void shutdownJobs() throws Exception {
		Scheduler sched = gSchedulerFactory.getScheduler();
		if (!sched.isShutdown()) {
			sched.shutdown();
		}
	}

	/**
	 * 判断cron时间表达式正确性
	 * 
	 * @param cronExpression
	 * @author hailongzhao
	 * @date 20151211
	 * @return
	 */
	public static boolean isValidExpression(final String cronExpression) {
		return CronExpression.isValidExpression(cronExpression);
//		try {
//			Trigger trigger = TriggerBuilder
//					.newTrigger()
//					.withIdentity("testname", "testgroup")
//					.startNow()
//					.withSchedule(
//							CronScheduleBuilder.cronSchedule(cronExpression))
//					.build();
//			Date date = trigger.getNextFireTime();
//			return date != null && date.after(new Date());
//		} catch (Exception e) {
//		}
//		return false;
	}
}
