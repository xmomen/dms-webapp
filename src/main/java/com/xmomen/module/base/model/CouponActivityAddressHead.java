package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

public @Data class CouponActivityAddressHead implements Serializable{
	/**
     * 卡
     */
    private String couponNumber;
    
    private List<CouponActivityAddress> couponActivityAddressList;
    
}
