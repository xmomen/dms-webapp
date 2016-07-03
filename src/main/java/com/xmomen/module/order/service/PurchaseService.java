package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.order.entity.*;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.model.OrderPurchaseModel;
import com.xmomen.module.order.model.PurchaseModel;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class PurchaseService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 查询采购单
     * @param keyword
     * @param limit
     * @param offset
     * @return
     */
    public Page<PurchaseModel> getPurchaseList(Map param, Integer limit, Integer offset){
        return (Page<PurchaseModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getPurchaseList", param, limit, offset);
    }

    /**
     * 创建采购单
     * @param createPurchase
     * @return
     */
    @Transactional
    public void createPurchase(CreatePurchase createPurchase){
        Map param = new HashMap();
        param.put("startTime", new Date(createPurchase.getOrderDate().getTime() - 24 * 3600 * 1000));
        param.put("endTime", createPurchase.getOrderDate());
        List<OrderPurchaseModel> purchaseModelList = mybatisDao.getSqlSessionTemplate().selectList(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderPurchaseList", param);
        if(CollectionUtils.isEmpty(purchaseModelList)){
            throw new IllegalArgumentException("今天没有需要生成的采购计划");
        }
        Map<String, TbPurchase> tbPurchaseMap = new HashMap<String, TbPurchase>();
        List<String> orderNoList = new ArrayList<String>();
        String purchaseCode = DateUtils.getDateTimeString();
        for (OrderPurchaseModel purchaseModel : purchaseModelList) {
            if(tbPurchaseMap.get(purchaseModel.getItemCode()) == null){
                TbPurchase tbPurchase = new TbPurchase();
                tbPurchase.setPurchaseCode(purchaseCode);
                tbPurchase.setCreateDate(mybatisDao.getSysdate());
                tbPurchase.setPurchaseStatus(0);
                tbPurchase.setItemCode(purchaseModel.getItemCode());
                tbPurchase.setTotal(purchaseModel.getTotalItemQty());
                tbPurchaseMap.put(purchaseModel.getItemCode(), tbPurchase);
            }else{
                BigDecimal total = tbPurchaseMap.get(purchaseModel.getItemCode()).getTotal().add(purchaseModel.getTotalItemQty());
                BigDecimal totalWeight = tbPurchaseMap.get(purchaseModel.getItemCode()).getTotalWeight().add(purchaseModel.getTotalWeight());
                tbPurchaseMap.get(purchaseModel.getItemCode()).setTotal(total);
                tbPurchaseMap.get(purchaseModel.getItemCode()).setTotalWeight(totalWeight);
            }
            orderNoList.add(purchaseModel.getOrderNo());
        }
        for (TbPurchase tbPurchase : tbPurchaseMap.values()) {
            mybatisDao.insert(tbPurchase);
        }
        //去除重复的订单号
        HashSet h = new HashSet(orderNoList);  
        orderNoList.clear();  
        orderNoList.addAll(h);  
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus("6");// 待采购
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria()
                .andOrderStatusEqualTo("1")
                .andOrderNoIn(orderNoList);
        mybatisDao.updateByExampleSelective(tbOrder, tbOrderExample);
        for (String orderNo : orderNoList) {
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setRefType("ORDER_PURCHASE_CODE");
            tbOrderRelation.setOrderNo(orderNo);
            tbOrderRelation.setRefValue(purchaseCode);
            mybatisDao.insert(tbOrderRelation);
        }
    }

    @Transactional
    public void updatePurchaseStatus(Integer id, Integer purchaseStatus){
        TbPurchaseExample tbPurchaseExample = new TbPurchaseExample();
        tbPurchaseExample.createCriteria().andIdEqualTo(id);
        TbPurchase tbPurchase = new TbPurchase();
        tbPurchase.setPurchaseStatus(purchaseStatus);
        mybatisDao.updateOneByExampleSelective(tbPurchase, tbPurchaseExample);
    }

    /**
     * 删除采购单
     * @param id
     */
    public void deletePurchase(Integer id){
        mybatisDao.deleteByPrimaryKey(TbPurchase.class, id);
    }

}
