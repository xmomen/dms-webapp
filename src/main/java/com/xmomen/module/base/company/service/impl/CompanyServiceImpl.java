package com.xmomen.module.base.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.company.model.CreateCompany;
import com.xmomen.module.base.company.model.UpdateCompany;
import com.xmomen.module.base.company.service.CompanyService;
import com.xmomen.module.base.entity.CdCompany;
import com.xmomen.module.base.entity.CdMember;
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createCompany(CreateCompany createCompany) {
		CdCompany company = new CdCompany();
		company.setCompanyCode(createCompany.getCompanyCode());
		company.setCompanyName(createCompany.getCompanyName());
		company.setCompanyLeader(createCompany.getCompanyLeader());
		company.setCompanyLeaderTel(createCompany.getCompanyLeaderTel());
		company.setCompanyAddress(createCompany.getCompanyAddress());
		mybatisDao.save(company);
	}
	@Transactional
	public void updateCompany(Integer id, UpdateCompany updateCompany) {
		CdCompany company = new CdCompany();
		company.setId(id);
		company.setCompanyCode(updateCompany.getCompanyCode());
		company.setCompanyName(updateCompany.getCompanyName());
		company.setCompanyLeader(updateCompany.getCompanyLeader());
		company.setCompanyLeaderTel(updateCompany.getCompanyLeaderTel());
		company.setCompanyAddress(updateCompany.getCompanyAddress());
		mybatisDao.update(company);
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdCompany.class, id);		
	}

}
