package com.xmomen.module.schedule.controller.vo;

/**
 * Created by Jeng on 2016/2/23.
 */
public class UpdateQuartzJob {

    public static String ACTION_PAUSE = "pause";
    public static String ACTION_START = "start";
    public static String ACTION_RESTART = "restart";
    public static String ACTION_STOP = "stop";

    // 动作
    private String action;
    // 任务名称
    private String jobName;
    // 任务分组
    private String jobGroup;
    // 任务触发器ID
    private String jobTriggerId;
    // 任务状态 0-禁用，1-启用，2-删除
    private Integer jobStatus;
    // 运行时间表达式
    private String cronExpression;
    // 任务描述
    private String description;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobTriggerId() {
        return jobTriggerId;
    }

    public void setJobTriggerId(String jobTriggerId) {
        this.jobTriggerId = jobTriggerId;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
