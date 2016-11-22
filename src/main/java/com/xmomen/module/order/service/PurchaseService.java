package com.xmomen.module.order.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderExample;
import com.xmomen.module.order.entity.TbOrderRelation;
import com.xmomen.module.order.entity.TbPurchase;
import com.xmomen.module.order.entity.TbPurchaseExample;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.model.OrderPurchaseModel;
import com.xmomen.module.order.model.PurchaseModel;

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
     * 查询采购单
     * @param keyword
     * @param limit
     * @param offset
     * @return
     */
    public List<PurchaseModel> getPurchaseList(Map param){
        return  mybatisDao.getSqlSessionTemplate().selectList(OrderMapper.ORDER_MAPPER_NAMESPACE + "getPurchaseList", param);
    }
    
    /**
     * 创建采购单
     * @param createPurchase
     * @return
     */
    @Transactional
    public void createPurchase(CreatePurchase createPurchase){
        Map param = new HashMap();

   	 	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(createPurchase.getAppointmentTimeStart()!=null){
        	  param.put("startTime",format.format(createPurchase.getAppointmentTimeStart()));
              param.put("endTime", format.format(createPurchase.getAppointmentTimeEnd()));
        }
        else{
            param.put("startTime", format.format(createPurchase.getOrderDate()));
            param.put("endTime", format.format(new Date(createPurchase.getOrderDate().getTime() + 24 * 3600 * 1000)));
        }
        List<OrderPurchaseModel> purchaseModelList = mybatisDao.getSqlSessionTemplate().selectList(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderPurchaseList", param);
        if(CollectionUtils.isEmpty(purchaseModelList)){
        	if(createPurchase.getAppointmentTimeStart()!=null){
        		throw new IllegalArgumentException("选定的配送日期内没有需要生成采购计划的订单");
        	}else{
        		throw new IllegalArgumentException("今天没有需要生成采购计划的订单");
        	}
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
                tbPurchase.setTotalWeight(purchaseModel.getTotalWeight());
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
        tbOrder.setOrderStatus("2");//待采购
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
