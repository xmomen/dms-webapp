package com.xmomen.module.product.model;

import lombok.Data;

public @Data class ProductQueryFilter {

	private String filterType;
	private String filter;
	private String condition;
}
