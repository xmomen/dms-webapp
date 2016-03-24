package com.xmomen.module.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

/**
 * 任务管理器，当前仅支持一个任务对应一个触发器，如需要定义复杂任务，需添加相应方法
 * Created by Jeng on 2016/2/23.
 */
public class QuartzManager {
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

    /**
     * 添加定时任务
     * @param jobName
     * @param jobGroup
     * @param cls
     * @param time
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, String jobGroup, Class cls, String time) throws SchedulerException, ParseException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        JobDetail jobDetail = scheduler.getJobDetail(jobName, jobGroup);
        if(jobDetail != null){
            throw new SchedulerException("exist this jobDetail, jobName: " + jobName +", jobGroup: " + jobGroup +"");
        }
        jobDetail = new JobDetail(jobName, jobGroup, cls);// 任务名，任务组，任务执行类
        // 触发器
        CronTrigger trigger = new CronTrigger(jobName, jobGroup);// 触发器名,触发器组
        trigger.setCronExpression(time);// 触发器时间设定
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 修改一个任务的触发时间
     * @param triggerName
     * @param triggerGroupName
     * @param time
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) throws SchedulerException, ParseException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerName,triggerGroupName);
        if (trigger == null) {
            return;
        }
        String oldTime = trigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronTrigger ct = (CronTrigger) trigger;
            // 修改时间
            ct.setCronExpression(time);
            // 重启触发器
            scheduler.resumeTrigger(triggerName, triggerGroupName);
        }
    }

    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName
     * @throws SchedulerException
     */
    public static void removeJob(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        if(scheduler.getJobDetail(jobName, jobGroup) == null){
            return;
        }
        if(Trigger.STATE_PAUSED != scheduler.getTriggerState(jobName, jobGroup)){
            scheduler.pauseTrigger(jobName, jobGroup);// 停止触发器
        }
        scheduler.unscheduleJob(jobName, jobGroup);// 移除触发器
        scheduler.deleteJob(jobName, jobGroup);// 删除任务
    }

    /**
     * 启动任务调度器中所有定时任务
     * @throws SchedulerException
     */
    public static void startJobs() throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        scheduler.start();
    }

    /**
     * 关闭任务调度器
     * @throws SchedulerException
     */
    public static void shutdownJobs() throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public static void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        if(Trigger.STATE_PAUSED != scheduler.getTriggerState(jobName, jobGroup)){
            scheduler.pauseJob(jobName, jobGroup);
        }
    }

    /**
     * 停止任务
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public static void unscheduleJob(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        scheduler.unscheduleJob(jobName, jobGroup);// 停止触发器
    }

    /**
     * 恢复已暂停的定时任务
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public static void resumeJob(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        scheduler.resumeJob(jobName, jobGroup);
    }

    /**
     * 获取任务触发器状态
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public static int getJobTriggerState(String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        return scheduler.getTriggerState(jobName, jobGroup);
    }

    /**
     * 获取scheduler任务调度器
     * @return
     * @throws SchedulerException
     */
    public static Scheduler getScheduler() throws SchedulerException {
        return gSchedulerFactory.getScheduler();
    }

    /**
     * 获取任务明细对象
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public static JobDetail getJobDetail(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        return scheduler.getJobDetail(jobName, jobGroup);
    }

    /**
     * 获取触发器对象
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public static Trigger getTrigger(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = gSchedulerFactory.getScheduler();
        return scheduler.getTrigger(jobName, jobGroup);
    }

    /**
     * 是否正在运行
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public static boolean isRunning(String jobName, String jobGroup) throws SchedulerException {
        if(Trigger.STATE_NORMAL == getJobTriggerState(jobName, jobGroup)){
            return true;
        }
        return false;
    }

}
