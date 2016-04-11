package com.xmomen.module.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdCompany;

@RestController
public class PublicController {
	
	@Autowired
    MybatisDao mybatisDao;
	@RequestMapping(value = "/companyList", method = RequestMethod.GET)
	public List<CdCompany> getCompany(){
		CdCompany company = new CdCompany();
		List<CdCompany> companys = mybatisDao.selectByModel(company);
		return companys;
	}
}
