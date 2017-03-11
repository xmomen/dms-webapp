package com.xmomen.module.base.service.impl;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.mapper.ItemMapper;
import com.xmomen.module.base.model.*;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdCouponCategoryRef;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.entity.CdItemRef;
import com.xmomen.module.base.entity.CdItemRefExample;
import com.xmomen.module.base.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	MybatisDao mybatisDao;

	@Override
	public List<ItemModel> queryItemList(ItemQuery itemQuery) {
		return mybatisDao.getSqlSessionTemplate().selectList(ItemMapper.ItemMapperNameSpace + "getItemList", itemQuery);
	}

	@Override
	public Page<ItemModel> queryItemList(ItemQuery itemQuery, Integer offset, Integer limit) {
		return (Page<ItemModel>) mybatisDao.selectPage(ItemMapper.ItemMapperNameSpace + "getItemList", itemQuery, limit, offset);
	}

	@Override
	@Transactional
	public void createItem(CreateItem createItem) {
		CdItem item = new CdItem();
		item.setBasePrice(createItem.getBasePrice());
		item.setCdCategoryId(createItem.getCdCategoryId());
		item.setIsAudit(createItem.getIsAudit());
		item.setItemCode(createItem.getItemCode());
		item.setItemDescribe(createItem.getItemDescribe());
		item.setItemName(createItem.getItemName());
		item.setItemType(createItem.getItemType());
		item.setMemberPrice(createItem.getMemberPrice());
		item.setPricingManner(createItem.getPricingManner());
		item.setSellPrice(createItem.getSellPrice());
		item.setSellStatus(createItem.getSellStatus());
		item.setSpec(createItem.getSpec());
		item.setSellUnit(createItem.getSellUnit());
		item.setYieldly(createItem.getYieldly());
        String username = (String) SecurityUtils.getSubject().getPrincipal();
		item.setCreateUserCode(username);
		item.setCaizaiUser(createItem.getCaizaiUser());
		item.setJianceUser(createItem.getJianceUser());
		item.setNongCanLv(createItem.getNongCanLv());
		item.setBaoZhiQi(createItem.getBaoZhiQi());
		item.setYiYangChenFen(createItem.getYiYangChenFen());
		item.setShiYiRenQun(createItem.getShiYiRenQun());
		item.setReMaiTuiJian(createItem.getReMaiTuiJian());
		item.setXinPinChangXian(createItem.getXinPinChangXian());
		item.setXianShiQiangGou(createItem.getXianShiQiangGou());
		item = mybatisDao.saveByModel(item);
		if(createItem.getChildItems() != null){
			for(ItemChildModel childItem : createItem.getChildItems()){
				CdItemRef itemRef = new CdItemRef();
				itemRef.setCdItemId(item.getId());
				itemRef.setRefType("CHILD_ITEM");
				itemRef.setRefValue(childItem.getId()+"");
				itemRef.setRefName("组合子商品");
				itemRef.setRefCount(childItem.getCount());
				mybatisDao.save(itemRef);
			}
		}
	}
	@Transactional
	public void updateItem(Integer id, UpdateItem updateItem) {
		CdItem item = new CdItem();
		item.setId(id);
		item.setBasePrice(updateItem.getBasePrice());
		item.setCdCategoryId(updateItem.getCdCategoryId());
		item.setIsAudit(updateItem.getIsAudit());
		item.setItemCode(updateItem.getItemCode());
		item.setItemDescribe(updateItem.getItemDescribe());
		item.setItemName(updateItem.getItemName());
		item.setItemType(updateItem.getItemType());
		item.setMemberPrice(updateItem.getMemberPrice());
		item.setPricingManner(updateItem.getPricingManner());
		item.setSellPrice(updateItem.getSellPrice());
		item.setSellStatus(updateItem.getSellStatus());
		item.setSpec(updateItem.getSpec());
		item.setSellUnit(updateItem.getSellUnit());
		item.setYieldly(updateItem.getYieldly());
		item.setCaizaiUser(updateItem.getCaizaiUser());
		item.setJianceUser(updateItem.getJianceUser());
		item.setNongCanLv(updateItem.getNongCanLv());
		item.setBaoZhiQi(updateItem.getBaoZhiQi());
		item.setYiYangChenFen(updateItem.getYiYangChenFen());
		item.setShiYiRenQun(updateItem.getShiYiRenQun());
		item.setReMaiTuiJian(updateItem.getReMaiTuiJian());
		item.setXinPinChangXian(updateItem.getXinPinChangXian());
		item.setXianShiQiangGou(updateItem.getXianShiQiangGou());
		if(updateItem.getChildItems() != null){
			//先删除再添加
			CdItemRefExample itemRefDel = new CdItemRefExample();
			itemRefDel.createCriteria().andCdItemIdEqualTo(id);
			mybatisDao.deleteByExample(itemRefDel);
			for(ItemChildModel childItem : updateItem.getChildItems()){
				CdItemRef itemRef = new CdItemRef();
				itemRef.setCdItemId(item.getId());
				itemRef.setRefType("CHILD_ITEM");
				itemRef.setRefValue(childItem.getId()+"");
				itemRef.setRefName("组合子商品");
				itemRef.setRefCount(childItem.getCount());
				mybatisDao.save(itemRef);
			}
		}
		mybatisDao.update(item);
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdItem.class, id);		
	}

}
