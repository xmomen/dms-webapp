package com.xmomen.module.base.model;

import java.io.Serializable;

import lombok.Data;

public @Data class ItemChildModel implements Serializable{
	
	private Integer id;
	private String itemName;
	private String itemCode;
	private String categoryName;
	private Integer count;
	private Integer sellStatus;//状态0-下架 1-上架
	
}
