package com.xmomen.module.wx.module.order.model;

import java.util.Date;

import lombok.Data;

public @Data class MyOrderQuery {

	private String memberCode;
	private Integer status;
	
	private Date minOrderTime;
	private Date maxOrderTime;
	
	private Integer userId;
}
