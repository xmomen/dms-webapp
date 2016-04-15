package com.xmomen.module.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.model.CreateItem;
import com.xmomen.module.base.model.UpdateItem;
import com.xmomen.module.base.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createItem(CreateItem createItem) {
		CdItem item = new CdItem();
		item.setBasePrice(createItem.getBasePrice());
		item.setCdCategoryId(createItem.getCdCategoryId());
		item.setIsAudit(createItem.getIsAudit());
		item.setIsCombination(createItem.getIsCombination());
		item.setItemCode(createItem.getItemCode());
		item.setItemDescribe(createItem.getItemDescribe());
		item.setItemName(createItem.getItemName());
		item.setItemType(createItem.getItemType());
		item.setMemberPrice(createItem.getMemberPrice());
		item.setParentItemId(createItem.getParentItemId());
		item.setPricingManner(createItem.getPricingManner());
		item.setSellPrice(createItem.getSellPrice());
		item.setSellStatus(createItem.getSellStatus());
		item.setSpec(createItem.getSpec());
		item.setSellUnit(createItem.getSellUnit());
		item.setYieldly(createItem.getYieldly());
		mybatisDao.save(item);
	}
	@Transactional
	public void updateItem(Integer id, UpdateItem updateItem) {
		CdItem item = new CdItem();
		item.setId(id);
		item.setBasePrice(updateItem.getBasePrice());
		item.setCdCategoryId(updateItem.getCdCategoryId());
		item.setIsAudit(updateItem.getIsAudit());
		item.setIsCombination(updateItem.getIsCombination());
		item.setItemCode(updateItem.getItemCode());
		item.setItemDescribe(updateItem.getItemDescribe());
		item.setItemName(updateItem.getItemName());
		item.setItemType(updateItem.getItemType());
		item.setMemberPrice(updateItem.getMemberPrice());
		item.setParentItemId(updateItem.getParentItemId());
		item.setPricingManner(updateItem.getPricingManner());
		item.setSellPrice(updateItem.getSellPrice());
		item.setSellStatus(updateItem.getSellStatus());
		item.setSpec(updateItem.getSpec());
		item.setSellUnit(updateItem.getSellUnit());
		item.setYieldly(updateItem.getYieldly());
		mybatisDao.update(item);
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdItem.class, id);		
	}

}
