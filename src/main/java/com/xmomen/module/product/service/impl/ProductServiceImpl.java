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
		Page<ProductModel> pageModel = (Page<ProductModel>) mybatisDao.selectPage(ProductMapper.ProductMapperNameSpace + "getProductList", productQuery, limit, offset);
		List<ProductModel> products = pageModel.getResult();
		//TODO mock data
		if(products != null) {
			for(ProductModel product: products) {
				product.setPicUrl("http://pic.58pic.com/58pic/15/35/55/12p58PICZv8_1024.jpg");
			}
		}
		return pageModel;
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
			//TODO mock
			picUrls.add("http://pic.58pic.com/58pic/15/35/55/12p58PICZv8_1024.jpg");
			picUrls.add("http://www.cqsxsp.com/images/201410/goods_img/68_P_1413356080309.jpg");
			picUrls.add("http://pic.58pic.com/58pic/15/38/18/52e58PICDE4_1024.jpg");
			detail.setPicUrls(picUrls);
			detail.setPicUrl(null);
			return detail;
		}
		return null;
	}
}
