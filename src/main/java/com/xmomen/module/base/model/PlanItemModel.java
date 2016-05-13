package com.xmomen.module.base.model;

import java.io.Serializable;

public class PlanItemModel implements Serializable{
	private Integer cdItemId;
	private String itemName;
	private String itemCode;
	private String categoryName;
	private Integer count;
	public Integer getCdItemId() {
		return cdItemId;
	}
	public void setCdItemId(Integer cdItemId) {
		this.cdItemId = cdItemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
