package com.xmomen.module.wx.module.order.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public @Data class PayOrderModel {

	@NotNull
    @NotBlank
	private Integer orderId;
	
	@NotNull
    @NotBlank
	private String paymentNo;
}
