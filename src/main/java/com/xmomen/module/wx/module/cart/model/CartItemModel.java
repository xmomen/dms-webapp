package com.xmomen.module.wx.module.cart.model;

import lombok.Data;

public @Data class CartItemModel {
	
	private String id;
	private String userToken;
	
    private Integer itemId;
    private Integer itemQty;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CartItemModel [itemId=");
		builder.append(itemId);
		builder.append(", itemQty=");
		builder.append(itemQty);
		builder.append("]");
		return builder.toString();
	}
    
    
}
