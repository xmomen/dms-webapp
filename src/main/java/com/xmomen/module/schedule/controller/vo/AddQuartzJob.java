package com.xmomen.module.schedule.controller.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Jeng on 2016/2/23.
 */
public class AddQuartzJob {

    // 任务名称
    @NotNull
    @NotBlank
    private String jobName;
    // 任务分组
    @NotNull
    @NotBlank
    private String jobGroup;
    // 任务触发器ID
    @NotNull
    @NotBlank
    private String jobTriggerId;
    // 任务状态 0-禁用，1-启用，2-删除
    @NotNull
    private Boolean started;
    // 运行时间表达式
    @NotNull
    @NotBlank
    private String cronExpression;
    // 任务描述
    @NotNull
    @NotBlank
    private String description;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
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
}
