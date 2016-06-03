package com.xmomen.module.system.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 2016/5/30.
 */
public @Data
class TaskModel implements Serializable{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

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
     * 执行人名称
     */
    private String executorName;

    /**
     * 任务状态：0-待分配，1-待执行，2-执行中，3-已完成，4-已取消
     */
    private Integer taskStatus;
}
