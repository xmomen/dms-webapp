package com.xmomen.module.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.product.entity.Category;
import com.xmomen.module.product.mapper.ProductCategoryMapper;
import com.xmomen.module.product.model.CategoryModel;
import com.xmomen.module.product.service.CategoryService;

/**
 * 
 * @author xiao
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    MybatisDao mybatisDao;
	
	public List<CategoryModel> getAllProductCategory() {
		List<Category> categoryList = mybatisDao.getSqlSessionTemplate().selectList(ProductCategoryMapper.ProductCategoryMapperNameSpace + "getProductCategoryList");
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		if(categoryList != null) {
			Map<String, CategoryModel> topCategories = new TreeMap<String, CategoryModel>();
			for(Category category: categoryList) {
				CategoryModel model = new CategoryModel();
				model.setId(category.getId());
				model.setLeaf(true);
				model.setName(category.getName());
				model.setChildren(new ArrayList<CategoryModel>());
				if(category.getParentId() == null) {
					topCategories.put(String.valueOf(category.getId()), model);
				} else {
					CategoryModel parentCategory = topCategories.get(String.valueOf(category.getParentId()));
					if(parentCategory != null) {
						parentCategory.setLeaf(false);
						parentCategory.getChildren().add(model);
					}
				}
				
			}
			categories.addAll(topCategories.values());
		}
		return categories;
	}
}
