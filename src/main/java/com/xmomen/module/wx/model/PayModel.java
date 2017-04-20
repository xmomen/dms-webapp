package com.xmomen.module.wx.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

public @Data class PayModel {

	/**
	 * 订单编号，如果为充值可不填，由后台自动生成UUID
	 */
	String outTradeNo;
	/**
	 * 订单金额，单位为分
	 */
	@NotNull
	Integer totalFee;
	/**
	 * 微信用户openId
	 */
	@NotNull
	String openId;
	/**
	 * 1 为支付
	 * 2 为充值
	 */
	@NotNull
	Integer type;
}
