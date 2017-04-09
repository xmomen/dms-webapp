package com.xmomen.module.product.service;

import java.util.List;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;

public interface ProductService {
	public Page<ProductModel> getProductList(ProductQuery productQuery, Integer limit, Integer offset);
	
	public ProductModel getDetailById(Integer id);
	
	public List<ProductModel> getProducts(List<Integer> itemIds);
}
