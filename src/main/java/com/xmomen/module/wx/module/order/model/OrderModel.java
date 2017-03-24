package com.xmomen.module.wx.module.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class OrderModel {

	private Integer id;
	private String orderNo;
	private Integer orderType;
	private Integer orderSource;
	private Integer paymentMode;
	private String orderStatus;
	private Integer payStatus;
	private BigDecimal totalAmount;
	
	private Date createTime;
	private Date appointmentTime;
	
	private List<OrderProductItem> products;
}
