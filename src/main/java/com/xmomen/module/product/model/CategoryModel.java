package com.xmomen.module.product.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

public @Data 
class CategoryModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer parentId;
	private String parentName;
	private List<CategoryModel> nodes;
	private boolean leaf = true;
}
