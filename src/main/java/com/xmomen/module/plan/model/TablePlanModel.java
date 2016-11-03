package com.xmomen.module.plan.model;

import java.io.Serializable;
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
    /**
     * 所属客服经理
     */
    private String managerName;
    
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

}
