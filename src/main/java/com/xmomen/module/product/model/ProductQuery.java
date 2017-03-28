package com.xmomen.module.product.model;

import java.util.List;

import lombok.Data;

public @Data class ProductQuery {

	private Integer categoryId;
	private String keyword;
	private String orderField;
	private Boolean isAsc = true;
	private List<String> filterLabels;
	
	private List<Integer> productIds;
	private String memberCode;
	
}
