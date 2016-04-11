package com.xmomen.module.base.company.service;

import com.xmomen.module.base.company.model.CreateCompany;
import com.xmomen.module.base.company.model.UpdateCompany;

public interface CompanyService {
	public void createCompany(CreateCompany createCompany);
	
	public void updateCompany(Integer id,UpdateCompany updateCompany);
	
	public void delete(Integer id);
}	
