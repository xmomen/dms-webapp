package com.xmomen.module.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.product.model.ProductLabel;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.product.model.ProductQueryFilter;
import com.xmomen.module.product.service.ProductService;

@Controller
@RequestMapping(value = "/wx")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
	public Page<ProductModel> getProducts(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "categoryId", required= false) Integer categoryId,
		  	@RequestParam(value = "productIds", required= false) Integer[] productIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "isAsc", required = false, defaultValue="true") Boolean isAsc,
            @RequestParam(value = "labels", required = false) List<String> labels) {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setKeyword(keyword);
		if(productIds != null && productIds.length > 0){
			productQuery.setProductIds(Arrays.asList(productIds));
		}
		if(categoryId != null && categoryId > 0) {
			productQuery.setCategoryId(categoryId);
		}
		List<String> labelEntityFields = new ArrayList<String>();
		if(!CollectionUtils.isEmpty(labels)) {
			for(String labelStr: labels) {
				ProductLabel label = ProductLabel.enumOf(labelStr);
				if(label != null) {
					labelEntityFields.add(label.getEntityField());
				}
			}
		}
		if(!labelEntityFields.isEmpty()) {
			productQuery.setFilterLabels(labelEntityFields);
		} else {
			productQuery.setFilterLabels(null);
		}
		ProductQueryFilter orderFieldType = ProductQueryFilter.enumOf(orderField);
		if(orderFieldType != null) {
			productQuery.setOrderField(orderFieldType.getFieldName());
			productQuery.setIsAsc(isAsc);
		}
		return productService.getProductList(productQuery, limit, offset);
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ProductModel detail(@PathVariable(value="id") Integer productId) {
		return productService.getDetailById(productId);
	}
}
