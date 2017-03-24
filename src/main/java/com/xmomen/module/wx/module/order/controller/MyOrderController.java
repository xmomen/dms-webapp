package com.xmomen.module.wx.module.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.service.MyOrderService;

@Controller
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;

	@RequestMapping("/myOrder")
	@ResponseBody
	public List<OrderModel> myOrder(@RequestParam("memberCode") String memberCode) {
		return myOrderService.myOrder(memberCode);
	}
	
	@RequestMapping("/myOrder/{orderId}")
	@ResponseBody
	public  OrderDetailModel orderDetail(@PathVariable("orderId") Integer orderId) {
		return myOrderService.getOrderDetail(orderId);
	}
}
