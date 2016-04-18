package com.xmomen.module.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdContractItem;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.model.CreateContractItem;
import com.xmomen.module.base.model.UpdateContractItem;
import com.xmomen.module.base.service.ContractItemService;
@Service
public class ContractItemServiceImpl implements ContractItemService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createContractItem(CreateContractItem createContractItem) {
		if(1 == createContractItem.getAddType()){
			CdItem item = new CdItem();
			item.setCdCategoryId(createContractItem.getCdCategoryId());
			List<CdItem> items = mybatisDao.selectByModel(item);
			for(CdItem itemDb : items){
				saveContractItem(createContractItem,itemDb);
			}
		}else if(2 == createContractItem.getAddType()){
			CdItem item = new CdItem();
			item.setItemCode(createContractItem.getItemCode());
			item = mybatisDao.selectOneByModel(item);
			if(item != null){
				saveContractItem(createContractItem,item);
			}
		}
		
	}
	@Transactional
	private void saveContractItem(CreateContractItem createContractItem,CdItem item){
		//先查询item是否存在，存在则更新
		CdContractItem contractItem = new CdContractItem();
		contractItem.setCdItemId(item.getId());
		contractItem.setCdContractId(createContractItem.getCdContractId());
		contractItem = mybatisDao.selectOneByModel(contractItem);
		if(contractItem == null){
			contractItem = new CdContractItem();
			contractItem.setCdItemId(item.getId());
			contractItem.setCdContractId(createContractItem.getCdContractId());
			contractItem.setContractType(createContractItem.getContractType());
			contractItem.setContractValue(createContractItem.getContractValue());
			mybatisDao.save(contractItem);
		}else{
			contractItem.setContractType(createContractItem.getContractType());
			contractItem.setContractValue(createContractItem.getContractValue());
			mybatisDao.update(contractItem);
		}
	}
	
	@Transactional
	public void updateContractItem(Integer id, UpdateContractItem updateContractItem) {
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdContractItem.class, id);		
	}
}
