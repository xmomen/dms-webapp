package com.xmomen.module.base.service;

import com.xmomen.module.base.model.CreateContractItem;
import com.xmomen.module.base.model.UpdateContractItem;

public interface ContractItemService {
	
	public void createContractItem(CreateContractItem createContractItem);
	
	public void updateContractItem(Integer id,UpdateContractItem updateContractItem);
	
	public void delete(Integer id);
}	
