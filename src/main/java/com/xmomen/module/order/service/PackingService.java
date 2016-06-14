package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.entity.CdItemExample;
import com.xmomen.module.order.entity.*;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.*;
import com.xmomen.module.system.entity.SysTask;
import com.xmomen.module.system.model.CreateTask;
import com.xmomen.module.system.service.TaskService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class PackingService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    TaskService taskService;

    public Page<PackingTaskCount> getPackingTaskCountList(Object o, Integer limit, Integer offset){
        Map map = new HashMap();
        map.put("roleType", "zhuangxiangzu");
        return (Page<PackingTaskCount>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "countPackingTask", map, limit, offset);
    }

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
    public void dispatchPackingTask(PackingTask packingTask){
        for (String orderNo : packingTask.getOrderNos()) {
            CreateTask createTask = new CreateTask();
            createTask.setTaskHeadId(1);
            createTask.setExecutorId(packingTask.getPackingTaskUserId());
            SysTask sysTask = taskService.createTask(createTask);
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setOrderNo(orderNo);
            tbOrderRelation.setRefType(OrderMapper.ORDER_PACKING_TASK_RELATION_CODE);
            tbOrderRelation.setRefValue(String.valueOf(sysTask.getId()));
            mybatisDao.insert(tbOrderRelation);
        }
    }

    @Transactional
    public void cancelPackingTask(String[] orderNoArray){
        TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
        tbOrderRelationExample.createCriteria().andOrderNoIn(CollectionUtils.arrayToList(orderNoArray)).andRefTypeEqualTo(OrderMapper.ORDER_PACKING_TASK_RELATION_CODE);
        List<TbOrderRelation> tbOrderRelationList = mybatisDao.selectByExample(tbOrderRelationExample);
        Integer[] taskIds = {tbOrderRelationList.size()};
        for (int i = 0; i < tbOrderRelationList.size(); i++) {
            TbOrderRelation tbOrderRelation = tbOrderRelationList.get(i);
            mybatisDao.deleteByPrimaryKey(TbOrderRelation.class, tbOrderRelation.getId());
            taskIds[i] = Integer.valueOf(tbOrderRelation.getRefValue());
        }
        taskService.cancelTask(taskIds);
    }

    @Transactional
    public TbPackingRecord createRecord(CreatePackingRecord createPackingRecord){
        PackingOrderQuery packingOrderQuery = new PackingOrderQuery();
        String itemCode = createPackingRecord.getUpc().substring(0, 5);
        CdItem cdItem = new CdItem();
        cdItem.setItemCode(itemCode);
        cdItem = mybatisDao.selectOneByModel(cdItem);
        if(cdItem == null){
            throw new IllegalArgumentException("非法的UPC号码，未找到匹配商品编号");
        }
        TbOrderItem tbOrderItem = new TbOrderItem();
        tbOrderItem.setItemCode(itemCode);
        tbOrderItem.setOrderNo(createPackingRecord.getOrderNo());
        tbOrderItem = mybatisDao.selectOneByModel(tbOrderItem);
        if(tbOrderItem == null){
            throw new IllegalArgumentException("此订单未订购此商品");
        }
        packingOrderQuery.setOrderItemId(tbOrderItem.getId());
        PackingOrderModel packingRecordModel = getOnePackingOrder(packingOrderQuery);
        if(packingRecordModel != null && packingRecordModel.getItemQty().compareTo(packingRecordModel.getPackedItemQty()) == 0){
            throw new IllegalArgumentException("装箱数量已到达订单采购数量");
        }
        TbPackingRecord tbPackingRecord = new TbPackingRecord();
        tbPackingRecord.setPackingId(createPackingRecord.getPackingId());
        tbPackingRecord.setScanTime(mybatisDao.getSysdate());
        tbPackingRecord.setUpc(createPackingRecord.getUpc());
        tbPackingRecord.setOrderItemId(tbOrderItem.getId());
        return mybatisDao.insertByModel(tbPackingRecord);
    }

    @Transactional
    public void delete(Integer packingId){
        TbPackingRecordExample tbPackingRecordExample = new TbPackingRecordExample();
        tbPackingRecordExample.createCriteria().andPackingIdEqualTo(packingId);
        mybatisDao.deleteByExample(tbPackingRecordExample);
    }

    @Transactional
    public void deleteRecord(Integer recordId){
        mybatisDao.deleteByPrimaryKey(TbPackingRecord.class, recordId);
    }

    public PackingOrderModel getOnePackingOrder(PackingOrderQuery packingOrderQuery){
        return mybatisDao.getSqlSessionTemplate().selectOne(OrderMapper.ORDER_MAPPER_NAMESPACE + "queryPackingOrderItemModel", packingOrderQuery);
    }

    public Page<PackingOrderModel> queryPackingOrder(PackingOrderQuery packingOrderQuery, Integer limit, Integer offset){
        return (Page<PackingOrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "queryPackingOrderItemModel", packingOrderQuery, limit, offset);
    }

    public Page<PackingRecordModel> queryPackingRecord(PackingRecordQuery queryPackingRecord, Integer limit, Integer offset){
        return (Page<PackingRecordModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "queryPackingRecordModel", queryPackingRecord, limit, offset);
    }
}
