package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

public @Data class TradeRecordModel implements Serializable{
	 /**
     * 主键
     */
    private Integer id;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 交易时间
     */
    private Date createTime;

    /**
     * 交易类型：cash-现金，card-卡券,recharge-充值，adjustment-卡余额调整
     */
    private String tradeType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录人
     */
    private Integer recordUser;
    
    private String recordUserName;
}
