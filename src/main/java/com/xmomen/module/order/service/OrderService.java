package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.entity.CdItemExample;
import com.xmomen.module.base.model.ItemModel;
import com.xmomen.module.base.model.ItemQuery;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.entity.TbOrderRelation;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.OrderModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    ItemService itemService;

    /**
     * 查询订单
     * @param keyword
     * @param limit
     * @param offset
     * @return
     */
    public Page<OrderModel> getOrderList(String keyword, Integer limit, Integer offset){
        Map param = new HashMap();
        param.put("keyword", keyword);
        return (Page<OrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderList", param, limit, offset);
    }

    /**
     * 创建订单
     * @param createOrder
     * @return
     */
    public TbOrder createOrder(CreateOrder createOrder){
        String orderNo = DateUtils.getDateTimeString();
        List<Integer> itemIdList = new ArrayList<Integer>();
        for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
            itemIdList.add(orderItem.getOrderItemId());
        }
        ItemQuery itemQuery = new ItemQuery();
        itemQuery.setIds((Integer[]) itemIdList.toArray());
        List<ItemModel> itemList = itemService.queryItemList(itemQuery);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ItemModel cdItem : itemList) {
            for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
                if(cdItem.getId().equals(orderItem.getOrderItemId())){
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getPricingManner());
                    if(cdItem.getDiscountPrice() != null){
                        tbOrderItem.setItemPrice(cdItem.getDiscountPrice());
                    }else{
                        tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    }
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
        tbOrder.setMemberCode(createOrder.getMemberCode());
        tbOrder.setRemark(createOrder.getRemark());
        tbOrder.setOrderType(createOrder.getOrderType());
        tbOrder.setOrderNo(orderNo);
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setCreateUserId(createOrder.getCreateUserId());
        tbOrder.setTotalAmount(totalAmount);
        tbOrder.setAppointmentTime(createOrder.getAppointmentTime());
        tbOrder = mybatisDao.insertByModel(tbOrder);
        if(StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null){
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setOrderNo(orderNo);
            tbOrderRelation.setRefType("ORDER_PAY_RELATION");// 订单支付关系
            tbOrderRelation.setRefValue(createOrder.getPaymentRelationNo());
            mybatisDao.insert(tbOrderRelation);
        }
        return tbOrder;
    }

    /**
     * 删除订单
     * @param id
     */
    public void deleteOrder(Integer id){
        mybatisDao.deleteByPrimaryKey(TbOrder.class, id);
    }

}
