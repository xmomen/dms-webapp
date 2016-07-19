package com.xmomen.module.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdCompany;
import com.xmomen.module.base.mapper.PublicMapper;
import com.xmomen.module.base.model.CompanyCustomerManager;

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
	public List<CompanyCustomerManager> getCustomerManager(
			@RequestParam(value = "userType", required = false) String userType,
			@RequestParam(value = "keyword", required = false) String keyword){
		 Map map = new HashMap<String,Object>();
		 map.put("userType", userType);
		 //客服经理过滤 如果有客服组权限则不过滤
        if(SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)){
			 Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
			 map.put("managerId", userId);
		 }
		if(StringUtils.trimToNull(keyword) != null){
			map.put("keyword", StringUtils.trimToEmpty(keyword));
		}
		List<CompanyCustomerManager> customerManagerList = mybatisDao.getSqlSessionTemplate().selectList(PublicMapper.PublicMapperNameSpace+"getManagerList", map);
		return customerManagerList;
	}
}
