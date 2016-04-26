package com.xmomen.module.base.entity;

import java.io.Serializable;

public class ChildItemModel implements Serializable {
	private Integer childItemId;
	private String itemName;
	private String itemCode;
	private String itemCategory;
	public Integer getChildItemId() {
		return childItemId;
	}
	public void setChildItemId(Integer childItemId) {
		this.childItemId = childItemId;
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
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

}
