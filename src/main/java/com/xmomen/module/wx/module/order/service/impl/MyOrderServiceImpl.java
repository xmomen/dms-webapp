package com.xmomen.module.wx.module.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.wx.module.order.mapper.MyOrderMapper;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.model.OrderProductItem;
import com.xmomen.module.wx.module.order.service.MyOrderService;

@Service
public class MyOrderServiceImpl implements MyOrderService {

	@Autowired
	MybatisDao mybatisDao;

	@Override
	public List<OrderModel> myOrder(String memberCode) {
		List<OrderModel> orders = mybatisDao.getSqlSessionTemplate().selectList(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "selectOrders", memberCode);
		if(orders != null) {
			for(OrderModel order: orders) {
				List<OrderProductItem> items = order.getProducts();
				if(items != null) {
					for(OrderProductItem item: items) {
						item.setPicUrl("http://pic.58pic.com/58pic/15/35/55/12p58PICZv8_1024.jpg");
					}
				}
			}
		}
		return orders;
	}

	@Override
	public OrderDetailModel getOrderDetail(Integer orderId) {
		OrderDetailModel orderDetail =mybatisDao.getSqlSessionTemplate().selectOne(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "getOrderDetail", orderId);
	    if(orderDetail != null) {
	    	List<OrderProductItem> items = orderDetail.getProducts();
	    	for(OrderProductItem item: items) {
				item.setPicUrl("http://pic.58pic.com/58pic/15/35/55/12p58PICZv8_1024.jpg");
			}
	    }
	    return orderDetail;
	}

	
}
