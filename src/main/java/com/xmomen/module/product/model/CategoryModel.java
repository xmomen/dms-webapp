package com.xmomen.module.product.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

public @Data 
class CategoryModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<CategoryModel> children;
	private boolean leaf = true;
}
