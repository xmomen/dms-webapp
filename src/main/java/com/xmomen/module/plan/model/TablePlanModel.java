package com.xmomen.module.plan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

public @Data class TablePlanModel implements Serializable{
	 /**
     * 
     */
    private Integer id;

    /**
     * 餐桌计划
     */
    private Integer cdPlanId;
    private String planName;

    /**
     * 审核状态（0-未审核，1-审核通过）
     */
    private Integer auditStatus;
    
    private Integer isStop;

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
     * 邮政编码
     */
    private String postcode;


    /**
     * 单位
     */
    private String companyName;
    
    private int companyId;
    
    /**
     * 所属客服经理
     */
    private String managerName;
    
    private int managerId;
    
    /**
     * 总配送次数
     */
    private Integer totalSendValue;

    /**
     * 已配送次数
     */
    private Integer sendValue;
    
    /**
     * 生效时间
     */
    private Date beginTime;
    
    /**
     * 配送频率（1周，2周，3周）
     */
    private Integer deliveryType;

    /**
     * 配送时间(一周的星期几）
     */
    private String sendWeekDay;
    /**
     * 上次配送时间
     */
    private Date lastSendDate;
    
    /**
     * 下次配送时间
     */
    private Date nextSendDate;
    /**
     * 是否随机
     */
    private Integer isRandom;

    /**
     * 产品归属的类别
     */
    private Integer cdCategoryId;

    /**
     * 随机数
     */
    private Integer randomNum;
    
    /**
     * 计划的金额
     */
    private BigDecimal price;
}
