package com.xmomen.module.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.product.mapper.ProductMapper;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	MybatisDao mybatisDao;

	@SuppressWarnings("unchecked")
	@Override
	public Page<ProductModel> getProductList(ProductQuery productQuery, Integer limit, Integer offset) {
		return (Page<ProductModel>) mybatisDao.selectPage(ProductMapper.ProductMapperNameSpace + "getProductList", productQuery, limit, offset);
	}

	@Override
	public ProductModel getDetailById(Integer id) {
		List<ProductModel> products = mybatisDao.getSqlSessionTemplate().selectList(ProductMapper.ProductMapperNameSpace + "getProductDetail", id);
		List<String> picUrls = new ArrayList<String>();
		if(products != null && !products.isEmpty()) {
			for(ProductModel product: products) {
				if(!StringUtils.isEmpty(product.getPicUrl())) {
					picUrls.add(product.getPicUrl());
				}
			}
			ProductModel detail = products.get(0);
			detail.setPicUrls(picUrls);
			detail.setPicUrl(null);
			return detail;
		}
		return null;
	}
}
