package com.xmomen.module.wx.model;

import lombok.Data;

public @Data class PayAttachModel {

	public PayAttachModel() {}

	public PayAttachModel(int type, String tradeNo, String tradeId, String openId, int memberId) {
		this.type = type;
		this.tradeNo = tradeNo;
		this.tradeId = tradeId;
		this.openId = openId;
		this.memberId = memberId;
	}
	String tradeId;
	int type;
	String tradeNo;
	String openId;
	int memberId;
	
}
