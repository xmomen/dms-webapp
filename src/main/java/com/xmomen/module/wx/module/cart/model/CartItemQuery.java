package com.xmomen.module.wx.module.cart.model;

import lombok.Data;

public @Data class CartItemQuery {

	private String userToken;
	private Integer itemId;
}
