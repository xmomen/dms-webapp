package com.xmomen.module.wx.module.order.controller;

import java.util.List;

import com.xmomen.module.base.constant.AppConstants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.service.MyOrderService;

@RestController
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;

	@RequestMapping(value = "/myOrder", method = RequestMethod.GET)
	@ResponseBody
	public List<OrderModel> myOrder(@RequestParam("memberCode") String memberCode) {
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		return myOrderService.myOrder(memberCode);
	}
	
	@RequestMapping(value = "/myOrder/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public OrderDetailModel orderDetail(@PathVariable("orderId") Integer orderId) {
		return myOrderService.getOrderDetail(orderId);
	}
}
