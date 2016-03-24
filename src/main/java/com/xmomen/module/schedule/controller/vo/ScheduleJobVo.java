package com.xmomen.module.schedule.controller.vo;

/**
 * Created by Jeng on 2016/2/25.
 */
public class ScheduleJobVo {

    private Integer id;
    private String jobName;
    private String jobGroup;
    private String triggerId;
    private Integer jobStatus;
    private String jobStatusDesc;
    private String cronExpression;
    private String jobDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatusDesc() {
        return jobStatusDesc;
    }

    public void setJobStatusDesc(String jobStatusDesc) {
        this.jobStatusDesc = jobStatusDesc;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
