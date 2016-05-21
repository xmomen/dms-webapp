package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.order.entity.*;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class PackingService {

    @Autowired
    MybatisDao mybatisDao;

    public Page<PackingModel> getPackingList(PackingQuery packingQuery, Integer limit, Integer offset){
        return (Page<PackingModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "queryPackingModel", packingQuery, limit, offset);
    }

    @Transactional
    public TbPacking create(CreatePacking createPacking){
        TbPacking tbPacking = new TbPacking();
        tbPacking.setPackingNo(DateUtils.getDateTimeString());
        tbPacking.setPackingStatus(0);
        tbPacking = mybatisDao.insertByModel(tbPacking);
        TbOrderRelation tbOrderRelation = new TbOrderRelation();
        tbOrderRelation.setOrderNo(createPacking.getOrderNo());
        tbOrderRelation.setRefType(OrderMapper.ORDER_PACKING_RELATION_CODE);
        tbOrderRelation.setRefValue(tbPacking.getPackingNo());
        mybatisDao.insert(tbOrderRelation);
        return tbPacking;
    }

    @Transactional
    public void delete(Integer packingId){
        TbPackingRecordExample tbPackingRecordExample = new TbPackingRecordExample();
        tbPackingRecordExample.createCriteria().andPackingIdEqualTo(packingId);
        mybatisDao.deleteByExample(tbPackingRecordExample);
        mybatisDao.deleteByPrimaryKey(TbPacking.class, packingId);
    }

    public Page<PackingOrderModel> queryPackingOrder(PackingOrderQuery packingOrderQuery, Integer limit, Integer offset){
        return (Page<PackingOrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "queryPackingOrderItemModel", packingOrderQuery, limit, offset);
    }

    public Page<PackingRecordModel> queryPackingRecord(PackingRecordQuery queryPackingRecord, Integer limit, Integer offset){
        return (Page<PackingRecordModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "queryPackingRecordModel", queryPackingRecord, limit, offset);
    }
}
