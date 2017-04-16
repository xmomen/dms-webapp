package com.xmomen.module.wx.module.order.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public @Data class PayOrderModel {

	@NotNull
	private Integer orderId;
	
	private String paymentNo;
	
	/**
	 * 卡类付款订单为1,其余为货到付款类型
	 */
	private Integer orderType;
}
