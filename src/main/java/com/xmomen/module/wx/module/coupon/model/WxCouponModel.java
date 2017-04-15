package com.xmomen.module.wx.module.coupon.model;

import java.math.BigDecimal;

import lombok.Data;

public @Data class WxCouponModel {

	private Integer id;
	private Integer couponType;
	private Integer couponCategory;
	private String couponNumber;
	private BigDecimal couponValue;
	private BigDecimal userPrice;
}
