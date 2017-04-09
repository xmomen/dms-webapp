package com.xmomen.module.wx.module.cart.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

public @Data class UpdateCartModel {

	@NotNull
	private String userToken;
	@NotNull
	private Integer itemId;
	private Integer itemNumber;
}
