package com.xmomen.module.base.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.entity.CdItemDetail;
import com.xmomen.module.base.mapper.ItemDetailMapper;
import com.xmomen.module.base.model.CreateItemDetail;
import com.xmomen.module.base.model.ItemDetailModel;
import com.xmomen.module.base.model.ItemDetailQuery;
import com.xmomen.module.base.model.UpdateItemDetail;
import com.xmomen.module.base.service.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemDetailServiceImpl implements ItemDetailService {
    @Autowired
    MybatisDao mybatisDao;

    @Override
    public List<ItemDetailModel> queryItemDetailList(ItemDetailQuery itemQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(ItemDetailMapper.ItemDetailMapperNameSpace + "getItemDetailList", itemQuery);
    }

    @Override
    public Page<ItemDetailModel> queryItemDetailList(ItemDetailQuery itemQuery, Integer offset, Integer limit) {
        return (Page<ItemDetailModel>) mybatisDao.selectPage(ItemDetailMapper.ItemDetailMapperNameSpace + "getItemDetailList", itemQuery, limit, offset);
    }

    @Override
    @Transactional
    public void createItemDetail(CreateItemDetail createItemDetail) {
        CdItemDetail itemDetail = new CdItemDetail();
        itemDetail.setItemDetailContent(createItemDetail.getItemDetailContent());
        itemDetail.setCdItemId(createItemDetail.getCdItemId());
        mybatisDao.save(itemDetail);
    }

    @Transactional
    public void updateItemDetail(Integer id, UpdateItemDetail updateItemDetail) {
        CdItemDetail itemDetail = new CdItemDetail();
        itemDetail.setId(id);
        itemDetail.setCdItemId(updateItemDetail.getCdItemId());
        itemDetail.setItemDetailContent(updateItemDetail.getItemDetailContent());
        mybatisDao.update(itemDetail);
    }

    @Transactional
    public void delete(Integer id) {
        mybatisDao.deleteByPrimaryKey(CdItemDetail.class, id);
    }

}
