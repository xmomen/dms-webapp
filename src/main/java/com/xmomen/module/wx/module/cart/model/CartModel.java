package com.xmomen.module.wx.module.cart.model;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Data;

/**
 * @author xiao
 *
 */
public @Data class CartModel {

	/**
	 * 微信用户memberId
	 */
	private String userToken;
	
	/**
	 * 购物车中的商品(一般购物车中数目有限，复制的开销不大,如果后期出现数据问题，可只存储改变量)
	 */
	private CopyOnWriteArrayList<CartMetadata> items;
	
	/**
	 * 上一次同步到数据库的时间
	 */
	private volatile Timestamp syncTime;
	
	/**
	 * 购物车的状态
	 * CLEAN -- 没有改动
	 * DIRTY -- 有修改
	 */
	private volatile String status;
}
