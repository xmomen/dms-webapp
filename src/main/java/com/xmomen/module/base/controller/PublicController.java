package com.xmomen.module.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdCompany;
import com.xmomen.module.base.mapper.PublicMapper;
import com.xmomen.module.user.entity.SysUsers;

@RestController
public class PublicController {
	
	@Autowired
    MybatisDao mybatisDao;
	
	@Autowired
    PublicMapper publicMapper;
	
	@RequestMapping(value = "/companyList", method = RequestMethod.GET)
	public List<CdCompany> getCompany(){
		CdCompany company = new CdCompany();
		List<CdCompany> companys = mybatisDao.selectByModel(company);
		return companys;
	}
	
	//查询客服经理
	@RequestMapping(value = "/customerManagerList", method = RequestMethod.GET)
	public List<SysUsers> getCustomerManager(){
		List<SysUsers> customerManagerList = publicMapper.getCustomerManager();
		return customerManagerList;
	}
}
