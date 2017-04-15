package com.xmomen.module.wx.module.order.service;

import java.util.List;

import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;

public interface MyOrderService {

	List<OrderModel> myOrder(MyOrderQuery myOrderQuery);
	OrderDetailModel getOrderDetail(MyOrderQuery myOrderQuery);
	
	Boolean confirmReceiveOrder(Integer orderId, Integer userId);
}
