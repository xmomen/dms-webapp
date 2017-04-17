package com.xmomen.module.wx.module.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.resource.service.ResourceUtilsService;
import com.xmomen.module.wx.module.order.mapper.MyOrderMapper;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.model.OrderProductItem;
import com.xmomen.module.wx.module.order.service.MyOrderService;

@Service
public class MyOrderServiceImpl implements MyOrderService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    OrderService orderService;

    @Override
    public List<OrderModel> myOrder(MyOrderQuery myOrderQuery) {
        List<OrderModel> orders = mybatisDao.getSqlSessionTemplate().selectList(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "selectOrders", myOrderQuery);
        if (orders != null) {
            for (OrderModel order : orders) {
                List<OrderProductItem> items = order.getProducts();
                if (items != null) {
                    for (OrderProductItem item : items) {
                    	if (StringUtils.isEmpty(item.getPicUrl())) {
                    		item.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                        }
                        else {
                        	item.setPicUrl(ResourceUtilsService.getWholeHttpPath(item.getPicUrl()));
                        }
                    }
                }
            }
        }
        return orders;
    }

    @Override
    public OrderDetailModel getOrderDetail(MyOrderQuery myOrderQuery) {
        if (myOrderQuery.getOrderId() == null && StringUtils.isEmpty(myOrderQuery.getOrderNo())) {
            return null;
        }
        OrderDetailModel orderDetail = mybatisDao.getSqlSessionTemplate().selectOne(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "getOrderDetail", myOrderQuery);
        if (orderDetail != null) {
            List<OrderProductItem> items = orderDetail.getProducts();
            for (OrderProductItem item : items) {
            	if (StringUtils.isEmpty(item.getPicUrl())) {
            		item.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                }
                else {
                	item.setPicUrl(ResourceUtilsService.getWholeHttpPath(item.getPicUrl()));
                }
            }
        }
        return orderDetail;
    }

    @Override
    public Boolean confirmReceiveOrder(Integer orderId, Integer userId) {
        TbOrder tbOrder = mybatisDao.selectByPrimaryKey(TbOrder.class, orderId);
        if (tbOrder == null || userId == null || !userId.equals(tbOrder.getMemberCode())) {
            throw new IllegalArgumentException("订单不存在或者不属于当前用户!");
        }
        tbOrder.setOrderStatus("6");//确认本人收货
        tbOrder.setShouHuoDate(new Date());
        mybatisDao.update(tbOrder);
        return Boolean.TRUE;
    }

    @Override
    public Boolean cancelOrder(Integer orderId, Integer userId) {
        TbOrder tbOrder = mybatisDao.selectByPrimaryKey(TbOrder.class, orderId);
        if (tbOrder == null || userId == null || !userId.equals(tbOrder.getMemberCode())) {
            throw new IllegalArgumentException("订单不存在或者不属于当前用户!");
        }
        Integer payStatus = tbOrder.getPayStatus();
        if (payStatus == 1) throw new IllegalArgumentException("订单已支付,不能取消!");
        tbOrder.setOrderStatus("9");//取消订单
        mybatisDao.update(tbOrder);
        return Boolean.TRUE;
    }

}
