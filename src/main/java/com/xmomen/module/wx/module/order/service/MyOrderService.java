package com.xmomen.module.wx.module.order.service;

import java.util.List;
import java.util.Map;

import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.pay.model.PayResData;

public interface MyOrderService {

	List<OrderModel> myOrder(MyOrderQuery myOrderQuery);
	OrderDetailModel getOrderDetail(MyOrderQuery myOrderQuery);
	
	Boolean confirmReceiveOrder(Integer orderId, Integer userId) throws Exception;
	
	Boolean cancelOrder(Integer orderId, Integer userId) throws Exception;
	
	Map<String, Integer> getOrderStatistic(Integer userId);
	
	void payCallBack(PayResData payResData) throws Exception;
}
