package com.xmomen.module.product.model;

import lombok.Data;

public @Data class ProductQuery {

	private Integer categoryId;
	private String keyword;
	private String orderField;
	private Integer orderDirection = 0;// 0 DESC, 1 ASC
	
}
