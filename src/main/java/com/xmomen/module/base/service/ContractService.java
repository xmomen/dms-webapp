package com.xmomen.module.base.service;

import com.xmomen.module.base.model.CreateContract;
import com.xmomen.module.base.model.CreateContractItem;
import com.xmomen.module.base.model.UpdateContract;

public interface ContractService {
	public void createContract(CreateContract createContract);
	
	public void updateContract(Integer id,UpdateContract updateContract);
	
	public void delete(Integer id);
	
}	
