package com.xmomen.module.wx.module.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class OrderModel {

	private Integer id;
	private String orderNo;
	private Integer orderType;
	private String orderTypeDesc;
	private Integer orderSource;
	private String orderSourceDesc;
	private Integer paymentMode;
	private String paymentModeDesc;
	private Integer otherPaymentMode;
	private String otherPaymentModeDesc;
	private String orderStatus;
	private String orderStatusDesc;
	private Integer payStatus;
	private String payStatusDesc;
	private BigDecimal totalAmount;
	
	private Date createTime;
	private Date appointmentTime;
	
	private List<OrderProductItem> products;
	private Boolean canCancel = false;
}
