package com.xmomen.module.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.module.product.model.CategoryModel;
import com.xmomen.module.product.service.CategoryService;

@Controller
@RequestMapping(value = "/wx")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryModel> getAllProductCategories() {
		return categoryService.getAllProductCategory();
	}
}
