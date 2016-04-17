package com.xmomen.module.base.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 16/3/26.
 */
public class UpdateCouponCategory implements Serializable {

    @NotNull
    @NotBlank
    private String categoryName;
    @NotNull
    private Integer categoryType;
   
    private List<CouponCategoryRefModel> categoryRefs;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public List<CouponCategoryRefModel> getCategoryRefs() {
		return categoryRefs;
	}

	public void setCategoryRefs(List<CouponCategoryRefModel> categoryRefs) {
		this.categoryRefs = categoryRefs;
	}
    

}
