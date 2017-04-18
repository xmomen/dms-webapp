package com.xmomen.module.wx.module.order.model;

import lombok.Data;

public @Data class OrderStatisticModel {

	private Integer orderStatus;
	private String orderStatusDesc;
	private Integer count;
	private Integer payStatus;
	private String payStatusDesc;
}
