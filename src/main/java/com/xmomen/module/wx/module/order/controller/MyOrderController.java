package com.xmomen.module.wx.module.order.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.model.WxCreateOrder;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.model.PayOrderModel;
import com.xmomen.module.wx.module.order.service.MyOrderService;

@Controller
@RequestMapping(value = "/wx/order")
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;
	
	@Autowired
	OrderService orderService;

	/**
	 * 
	 * @param memberId
	 * @param status 0-未支付 1 待收货
	 * @param minCreateTime 昨日
	 * @param maxCreateTime 今日
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<OrderModel> myOrder(@RequestParam(value = "memberId") String memberId,
			@RequestParam(value = "status", required = false) Integer status, 
			@RequestParam(value = "minOrderTime", required = false) Date minCreateTime,
			@RequestParam(value = "maxOrderTime", required = false) Date maxCreateTime) {
		MyOrderQuery myOrderQuery  = new MyOrderQuery();
		myOrderQuery.setStatus(status);
		myOrderQuery.setMinOrderTime(minCreateTime);
		myOrderQuery.setMaxOrderTime(maxCreateTime);
		myOrderQuery.setUserId(memberId);
		return myOrderService.myOrder(myOrderQuery);
	}
	
	@RequestMapping(value = "/{orderId:[0-9]+}", method = RequestMethod.GET)
	@ResponseBody
	public OrderDetailModel orderDetail(@PathVariable("orderId") Integer orderId) {
		MyOrderQuery myOrderQuery  = new MyOrderQuery();
		myOrderQuery.setOrderId(orderId);
		return myOrderService.getOrderDetail(myOrderQuery);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public TbOrder createModel(@RequestBody @Valid WxCreateOrder createOrder) throws Exception {
        return orderService.createWxOrder(createOrder);
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Boolean confirmOrder(@RequestParam("id") Integer orderId, @RequestParam("memberId") Integer memberId) throws Exception {
        return myOrderService.confirmReceiveOrder(orderId, memberId);
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public Boolean cancelOrder(@RequestParam("id") Integer orderId, @RequestParam("memberId") Integer memberId) throws Exception {
        return myOrderService.cancelOrder(orderId, memberId);
	}
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public Boolean payOrder(@RequestBody PayOrderModel payOrderModel) throws Exception {
		return orderService.payWxOrder(payOrderModel);
	}
	
	@RequestMapping(value = "/coupon", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductModel> getCouponItems(@RequestParam("couponNo") String couponNo) {
		return orderService.getCouponItems(couponNo);
	}
	
	/**
	 * 
	 * @param memberId
	 * @return {"待装箱":7,"待付款":6,"待采购":2,"待配送":2,"配送中":1}
	 */
	@RequestMapping(value = "/statistic", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getOrderStatistic(@RequestParam("memberId") Integer memberId) {
		return myOrderService.getOrderStatistic(memberId);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				super.setValue(new Date(Long.valueOf(text)));
			}
			
		});
	}
}
