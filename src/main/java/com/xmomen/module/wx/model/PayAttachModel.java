package com.xmomen.module.wx.model;

import lombok.Data;

public @Data class PayAttachModel {

	public PayAttachModel(int type, String tradeNo, String tradeId) {
		this.type = type;
		this.tradeNo = tradeNo;
		this.tradeId = tradeId;
	}
	String tradeId;
	int type;
	String tradeNo;
	
}
