package com.xmomen.module.wx.module.order.model;

import java.math.BigDecimal;

import lombok.Data;

public @Data class OrderProductItem {

	private String orderNo;
	private Integer itemId;
	private String itemName;
	private String itemCode;
	private BigDecimal itemPrice;
	private BigDecimal itemQty;
	private String itemUnit;

	private String picUrl;
}
