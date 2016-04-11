package com.xmomen.module.base.service;

import com.xmomen.module.base.model.CreateItem;
import com.xmomen.module.base.model.UpdateItem;

public interface ItemService {
	public void createItem(CreateItem createItem);
	
	public void updateItem(Integer id,UpdateItem updateItem);
	
	public void delete(Integer id);
}	
