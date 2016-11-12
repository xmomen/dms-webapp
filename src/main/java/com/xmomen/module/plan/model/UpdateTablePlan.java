package com.xmomen.module.plan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

public @Data class UpdateTablePlan implements Serializable{
    /**
     * 餐桌计划
     */
    private Integer cdPlanId;

    /**
     * 审核状态（0-未审核，1-审核通过）
     */
    private Integer auditStatus;

    /**
     * 是否暂停 0-不暂停，1-暂停
     */
    private Integer isStop;

    /**
     * 
     */
    private Integer cdMemberId;

    /**
     * 卡号
     */
    private String couponNumber;

    /**
     * 手机号
     */
    private String consigneePhone;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人地址
     */
    private String consigneeAddress;
    /**
     * 生效时间
     */
    private Date beginTime;
    
    private String sendWeekDay;
}
