package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.entity.CdItemExample;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class OrderService {

    @Autowired
    MybatisDao mybatisDao;

    public Page<OrderModel> getOrderList(String keyword, Integer limit, Integer offset){
        Map param = new HashMap();
        param.put("keyword", keyword);
        return (Page<OrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderList", param, limit, offset);
    }

    public TbOrder createOrder(CreateOrder createOrder){
        String orderNo = DateUtils.getDateTimeString();
        List<Integer> itemIdList = new ArrayList<Integer>();
        for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
            itemIdList.add(orderItem.getOrderItemId());
        }
        CdItemExample cdItemExample = new CdItemExample();
        cdItemExample.createCriteria().andIdIn(itemIdList);
        List<CdItem> itemList = mybatisDao.selectByExample(cdItemExample);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CdItem cdItem : itemList) {
            for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
                if(cdItem.getId().equals(orderItem.getOrderItemId())){
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getSellUnit());
                    totalAmount = totalAmount.add(tbOrderItem.getItemPrice().multiply(orderItem.getItemQty()));
                    mybatisDao.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus("1");// 订单新建
        tbOrder.setTransportMode(1);// 默认快递
        tbOrder.setConsigneeName(createOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(createOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(createOrder.getConsigneePhone());
        tbOrder.setCreateTime(mybatisDao.getSysdate());
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setPaymentMode(createOrder.getPaymentMode());
        tbOrder.setRemark(createOrder.getRemark());
        tbOrder.setOrderType(createOrder.getOrderType());
        tbOrder.setOrderNo(orderNo);
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setCreateUserId(createOrder.getCreateUserId());
        tbOrder.setTotalAmount(totalAmount);
        tbOrder = mybatisDao.insertByModel(tbOrder);
        return tbOrder;
    }

    public void deleteOrder(Integer id){

    }

}
