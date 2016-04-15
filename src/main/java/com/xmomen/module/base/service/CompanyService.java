package com.xmomen.module.base.service;

import com.xmomen.module.base.model.CreateCompany;
import com.xmomen.module.base.model.UpdateCompany;

public interface CompanyService {
	public void createCompany(CreateCompany createCompany);
	
	public void updateCompany(Integer id,UpdateCompany updateCompany);
	
	public void delete(Integer id);
}	
