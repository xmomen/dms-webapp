package com.xmomen.module.wx.module.cart.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

public @Data class CartModel {

	/**
	 * 微信用户openId
	 */
	private String userToken;
	
	/**
	 * 购物车中的商品
	 */
	private List<CartMetadata> items;
	
	/**
	 * 上一次同步到数据库的时间
	 */
	private Timestamp syncTime;
	
	/**
	 * 购物车的状态
	 * CLEAN -- 没有改动
	 * DIRTY -- 有修改
	 */
	private String status;
}
