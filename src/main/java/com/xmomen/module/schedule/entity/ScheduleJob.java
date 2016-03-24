package com.xmomen.module.schedule.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;

@Entity
@Table(name = "schedule_job")
public class ScheduleJob extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务属组
     */
    private String jobGroup;

    /**
     * 任务状态：0-禁用，1-启用，2-删除
     */
    private Integer jobStatus;

    /**
     * 任务描述
     */
    private String jobDescription;

    /**
     * CRON表达式
     */
    private String cronExpression;

    /**
     * 触发器ID
     */
    private String triggerId;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "JOB_NAME")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
        if(jobName == null){
              removeValidField("jobName");
              return;
        }
        addValidField("jobName");
    }

    @Column(name = "JOB_GROUP")
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
        if(jobGroup == null){
              removeValidField("jobGroup");
              return;
        }
        addValidField("jobGroup");
    }

    @Column(name = "JOB_STATUS")
    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
        if(jobStatus == null){
              removeValidField("jobStatus");
              return;
        }
        addValidField("jobStatus");
    }

    @Column(name = "JOB_DESCRIPTION")
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        if(jobDescription == null){
              removeValidField("jobDescription");
              return;
        }
        addValidField("jobDescription");
    }

    @Column(name = "CRON_EXPRESSION")
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        if(cronExpression == null){
              removeValidField("cronExpression");
              return;
        }
        addValidField("cronExpression");
    }

    @Column(name = "TRIGGER_ID")
    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        if(triggerId == null){
              removeValidField("triggerId");
              return;
        }
        addValidField("triggerId");
    }
}