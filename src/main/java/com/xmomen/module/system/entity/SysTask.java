package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_task")
public class SysTask extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 任务头ID
     */
    private Integer taskHeadId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 执行人ID
     */
    private Integer executorId;

    /**
     * 任务状态：0-待分配，1-待执行，2-执行中，3-已完成，4-已取消
     */
    private Integer taskStatus;

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

    @Column(name = "TASK_HEAD_ID")
    public Integer getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(Integer taskHeadId) {
        this.taskHeadId = taskHeadId;
        if(taskHeadId == null){
              removeValidField("taskHeadId");
              return;
        }
        addValidField("taskHeadId");
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime == null){
              removeValidField("createTime");
              return;
        }
        addValidField("createTime");
    }

    @Column(name = "START_TIME")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        if(startTime == null){
              removeValidField("startTime");
              return;
        }
        addValidField("startTime");
    }

    @Column(name = "FINISH_TIME")
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        if(finishTime == null){
              removeValidField("finishTime");
              return;
        }
        addValidField("finishTime");
    }

    @Column(name = "EXECUTOR_ID")
    public Integer getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Integer executorId) {
        this.executorId = executorId;
        if(executorId == null){
              removeValidField("executorId");
              return;
        }
        addValidField("executorId");
    }

    @Column(name = "TASK_STATUS")
    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
        if(taskStatus == null){
              removeValidField("taskStatus");
              return;
        }
        addValidField("taskStatus");
    }
}