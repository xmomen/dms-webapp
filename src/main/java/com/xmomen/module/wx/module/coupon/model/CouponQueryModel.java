package com.xmomen.module.wx.module.coupon.model;

import lombok.Data;

public @Data class CouponQueryModel {

	private Integer couponType;
	private Integer cdUserId;
	private Boolean useable;
}
