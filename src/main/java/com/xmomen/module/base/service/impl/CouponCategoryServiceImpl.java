package com.xmomen.module.base.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdCouponCategory;
import com.xmomen.module.base.entity.CdCouponCategoryExample;
import com.xmomen.module.base.entity.CdCouponCategoryRef;
import com.xmomen.module.base.model.CouponCategoryRefModel;
import com.xmomen.module.base.model.CreateCouponCategory;
import com.xmomen.module.base.model.UpdateCouponCategory;
import com.xmomen.module.base.service.CouponCategoryService;
@Service
public class CouponCategoryServiceImpl implements CouponCategoryService {
	@Autowired
	MybatisDao mybatisDao;
	
	 public Page<CdCouponCategory> getCouponCategoryList(String keyword, Integer limit, Integer offset){
	        CdCouponCategoryExample cdCouponCategoryExample = new CdCouponCategoryExample();
	        cdCouponCategoryExample.createCriteria().andCategoryNameLike("%" + StringUtils.trimToEmpty(keyword) + "%");
	        return mybatisDao.selectPageByExample(cdCouponCategoryExample, limit, offset);
	    }
	
	 public CdCouponCategory getCouponCategory(Integer id){
	        return mybatisDao.selectByPrimaryKey(CdCouponCategory.class, id);
	 }
	
	@Override
	@Transactional
	public CdCouponCategory createCouponCategory(CreateCouponCategory createCouponCategory) {
		CdCouponCategory category = new CdCouponCategory();
		category.setCategoryName(createCouponCategory.getCategoryName());
		category.setCategoryType(createCouponCategory.getCategoryType());
		category = mybatisDao.saveByModel(category);
		if(createCouponCategory.getCategoryRefs() != null){
			for(CouponCategoryRefModel categoryRefVo : createCouponCategory.getCategoryRefs()){
				CdCouponCategoryRef categoryRef = new CdCouponCategoryRef();
				categoryRef.setCdCouponCategoryId(category.getId());
				categoryRef.setRefType("TICKET_ITEM");
				categoryRef.setRefValue(categoryRefVo.getCdItemId()+"");
				categoryRef.setRefName("消费劵固定产品");
				mybatisDao.save(categoryRef);
			}
		}
		return category;
	}
	@Transactional
	public CdCouponCategory updateCouponCategory(Integer id, UpdateCouponCategory updateCouponCategory) {
		CdCouponCategory item = new CdCouponCategory();
		item.setId(id);
		return mybatisDao.updateByModel(item);
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdCouponCategory.class, id);		
	}

}
