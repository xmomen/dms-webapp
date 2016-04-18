package com.xmomen.module.base.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.entity.CdCouponCategory;
import com.xmomen.module.base.model.CreateCouponCategory;
import com.xmomen.module.base.model.UpdateCouponCategory;

public interface CouponCategoryService {
	public Page<CdCouponCategory> getCouponCategoryList(String keyword,Integer categoryType, Integer limit, Integer offset);
	
	public CdCouponCategory getCouponCategory(Integer id);
	
	public CdCouponCategory createCouponCategory(CreateCouponCategory createCouponCategory);
	
	public CdCouponCategory updateCouponCategory(Integer id,UpdateCouponCategory updateCouponCategory);
	
	public void delete(Integer id);
}	
