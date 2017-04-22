package com.xmomen.module.wx.pay.model;

import lombok.Data;

public @Data class WeixinPayRecord {

	private String tradeId;
	
	private Integer tradeType;
	/**
	 * 商品订单号
	 */
	private String tradeNo;
	/**
	 * 支付总金额
	 * 单位为分
	 */
	private Integer totalFee;
	
	private String openId;
	
	private String transactionId;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeixinPayRecord [tradeId=");
		builder.append(tradeId);
		builder.append(", tradeType=");
		builder.append(tradeType);
		builder.append(", tradeNo=");
		builder.append(tradeNo);
		builder.append(", totalFee=");
		builder.append(totalFee);
		builder.append(", openId=");
		builder.append(openId);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append("]");
		return builder.toString();
	}
}
