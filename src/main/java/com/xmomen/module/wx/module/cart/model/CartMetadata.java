package com.xmomen.module.wx.module.cart.model;

import java.sql.Timestamp;
import java.util.Date;

import com.xmomen.module.wx.util.Constant;

import lombok.Data;

public @Data class CartMetadata {
	
	private String userToken;
	/**
	 * 商品ID
	 */
	private Integer itemId;
	
	/**
	 * 商品数目
	 */
	private Integer itemQty = 0;
	
	/**
	 * MODIFY 数目改动，包含新增
	 * DELETE 物品删除
	 * CLEAN 已与DB同步了
	 */
	private volatile String status = Constant.CLEAN;
	
	private volatile Timestamp updateTime;
	
	
	/**
	 * 会自动根据数据更新状态和时间
	 * @param number
	 * return order是否有改变
	 */
	public boolean setItemQty(Integer number) {
		if(this.itemQty == null && this.itemQty == number) return false;
		if(this.itemQty != null && this.itemQty.equals(number)) return false;
		if(number == 0) {
			this.itemQty = number;
			this.status = Constant.DELETE;
			this.updateTime = new Timestamp(new Date().getTime());
			return true;
		} else if(number > 0) {
			this.itemQty = number;
			this.status = Constant.MODIFY;
			this.updateTime = new Timestamp(new Date().getTime());
			return true;
		}
		return false;
	}
}
