package com.xmomen.module.base.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.model.CreateItem;
import com.xmomen.module.base.model.ItemModel;
import com.xmomen.module.base.model.ItemQuery;
import com.xmomen.module.base.model.UpdateItem;

import java.util.List;

public interface ItemService {

	public List<ItemModel> queryItemList(ItemQuery itemQuery);

	public Page<ItemModel> queryItemList(ItemQuery itemQuery, Integer offset, Integer limit);

	public void createItem(CreateItem createItem);
	
	public void updateItem(Integer id,UpdateItem updateItem);
	
	public void delete(Integer id);
	
}	
