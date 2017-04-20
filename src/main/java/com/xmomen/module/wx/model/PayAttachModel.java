package com.xmomen.module.wx.model;

import lombok.Data;

public @Data class PayAttachModel {

	public PayAttachModel(int type, String tradeNo) {
		this.type = type;
		this.tradeNo = tradeNo;
	}
	int type;
	String tradeNo;
	
}
