package com.xmomen.module.wx.module.order.model;

import lombok.Data;

public @Data class WeixinPayResult {

	/**
	 * 商品订单号
	 */
	private String orderNo;
	/**
	 * 支付总金额
	 * 单位为元
	 */
	private Double totalFee;
}
