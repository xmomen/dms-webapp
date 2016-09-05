package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

public @Data class CouponActivityAddress implements Serializable {
	
    /**
     * 收货人
     */
    private String consignmentName;
    /**
     * 手机号
     */
    private String consignmentPhone;
    /**
     * 地址
     */
    private String consignmentAddress;

    /**
     * 赠送时间
     */
    private Date sendTime;
    
    /**
     * 赠送数量
     */
    private Integer sendCount;
    
}
