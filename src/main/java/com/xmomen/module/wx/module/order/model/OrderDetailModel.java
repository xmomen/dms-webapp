package com.xmomen.module.wx.module.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class OrderDetailModel {

	private Integer id;
	private String orderNo;
	private String orderType;
	private String orderTypeDesc;
	private Integer orderSource;
	private String orderSourceDesc;
	private Integer paymentMode;
	private String paymentModeDesc;
	private Integer otherPaymentMode;
	private String otherPaymentModeDesc;
	private String memberCode;
	private String orderStatus;
	private String orderStatusDesc;
	private Integer payStatus;
	private String payStatusDesc;
	private String consigneePhone;
	private String consigneeName;
	private String consigneeAddress;
	private String postcode;
	private Integer transportMode;
	private String transportModeDesc;
	private String remark;
	
	private BigDecimal discountPrice;
	private BigDecimal totalAmount;
	
	private Date createTime;
	private Date outDate;
	private Date shouHuoDate;
	private Date appointmentTime;
	
	private Boolean isReject;
	private String expressName;
	
	private String expressMemberName;
	private String expressMemberPhone;
	
	
	List<OrderProductItem> products;
	private Boolean canCancel;
}
