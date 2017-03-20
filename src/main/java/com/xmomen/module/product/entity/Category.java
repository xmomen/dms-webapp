package com.xmomen.module.product.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

@Entity
@Table(name="cd_category")
public class Category extends BaseMybatisModel {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append("]");
		return builder.toString();
	}
}
