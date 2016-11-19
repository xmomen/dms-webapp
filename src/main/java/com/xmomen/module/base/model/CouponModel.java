package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Created by Jeng on 2016/3/30.
 */
public @Data class CouponModel implements Serializable {

    /**
     *
     */
    private Integer id;

    /**
     * 卡或者劵,1-卡，2-劵
     */
    private Integer couponType;

    private String couponTypeDesc;
    
    private Integer couponCategoryId;
    
    private Integer couponCategory;

	private Integer memberId;
	
    private String categoryName;

    /**
     * 卡描述
     */
    private String couponDesc;

    /**
     * 卡号
     */
    private String couponNumber;

	/**
	 * 可用金额/可用次数
	 */
	private BigDecimal userPrice;

    /**
     * 卡值：初始金额,初始次数
     */
    private BigDecimal couponValue;
    
    /**
     * 最低赠送金额
     */
    private Integer lowestPrice;

    /**
     * 密码
     */
    private String couponPassword;

    /**
     * 有效开始时间
     */
    private Date beginTime;

    /**
     * 有效结束时间
     */
    private Date endTime;
    
    /**
     * 审核时间
     */
    private Date auditDate;

    /**
     * 0-未使用，1-已使用
     */
    private Integer isUsed;

    private String isUsedDesc;

    /**
     * 0-无效，1-有效
     */
    private Integer isUseful;
    /**
     * 0-无效，1-有效
     */
    private Integer isSend;
    
    /**
     * 客服经理id
     */
    private Integer managerId;
    /**
     * 发放单位Id
     */
	private Integer companyId;
    /**
     * 发放单位
     */
    private String companyName;
    
    /**
     * 发放客户经理
     */
    private String managerName;
    /**
     * 是否预付款 1-预付款,2-后付款
     */
    private Integer paymentType;
    
    private String consignmentName;
    
    private String consignmentPhone;
    
    private String consignmentAddress;
    
    private Date sendTime;
    
    private String receivedPrice;
    
    private String batch;
    
	private String isUsefulDesc;

    /**
     * 0-非赠送，1-赠送
     */
    private Integer isGift;

    /**
     *
     */
    private String isGiftDesc;
    /**
     *
     */
    private String notes;
    /**
     * 活动id
     */
    private int cdActivityId;
    
	private List<CouponRelationItem> relationItemList;

}
