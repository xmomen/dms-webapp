package com.xmomen.module.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/{categoryId}/list", method = RequestMethod.GET)
	@ResponseBody
	public Page<ProductModel> getProductsByCategory(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @PathVariable(value="categoryId") Integer categoryId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "orderDirection", required = false, defaultValue="0") Integer orderDirection) {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setKeyword(keyword);
		productQuery.setCategoryId(categoryId);
		/*productQuery.setOrderField(orderField);
		productQuery.setOrderDirection(orderDirection);*/
		return productService.getProductList(productQuery, limit, offset);
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Page<ProductModel> getProducts(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "orderDirection", required = false, defaultValue="0") Integer orderDirection) {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setKeyword(keyword);
		/*productQuery.setOrderField(orderField);
		productQuery.setOrderDirection(orderDirection);*/
		return productService.getProductList(productQuery, limit, offset);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductModel detail(@PathVariable(value="id") Integer productId) {
		return productService.getDetailById(productId);
	}
}
