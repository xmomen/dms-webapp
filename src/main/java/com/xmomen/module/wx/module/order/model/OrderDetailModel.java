package com.xmomen.module.wx.module.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class OrderDetailModel {

	private Integer id;
	private String orderNo;
	private String orderType;
	private Integer orderSource;
	private Integer paymentMode;
	private Integer otherPaymentMode;
	private String memberCode;
	private String orderStatus;
	private Integer payStatus;
	private String consigneePhone;
	private String consigneeName;
	private String consigneeAddress;
	private String postcode;
	private Integer transportMode;
	private String remark;
	
	private BigDecimal discountPrice;
	private BigDecimal totalAmount;
	
	private Date createTime;
	private Date outDate;
	private Date shouHuoDate;
	private Date appointmentTime;
	
	private Boolean isReject;
	
	
	List<OrderProductItem> products;
}
