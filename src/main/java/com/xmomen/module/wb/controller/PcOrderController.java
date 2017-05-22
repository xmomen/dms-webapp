package com.xmomen.module.wb.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.model.WxCreateOrder;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.model.PayOrderModel;
import com.xmomen.module.wx.module.order.service.MyOrderService;

@RestController
@RequestMapping(value = "/wb/order")
public class PcOrderController {

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
	public List<OrderModel> myOrder(@RequestParam(value = "status", required = false) Integer status, 
			@RequestParam(value = "minOrderTime", required = false) Date minCreateTime,
			@RequestParam(value = "maxOrderTime", required = false) Date maxCreateTime) {
		MyOrderQuery myOrderQuery  = new MyOrderQuery();
		myOrderQuery.setStatus(status);
		myOrderQuery.setMinOrderTime(minCreateTime);
		myOrderQuery.setMaxOrderTime(maxCreateTime);
		String memberId = String.valueOf(SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
		myOrderQuery.setUserId(memberId);
		return myOrderService.myOrder(myOrderQuery);
	}
	
	@RequestMapping(value = "/{orderId:[0-9]+}", method = RequestMethod.GET)
	@ResponseBody
	public OrderDetailModel orderDetail(@PathVariable("orderId") Integer orderId) {
		MyOrderQuery myOrderQuery  = new MyOrderQuery();
		myOrderQuery.setOrderId(orderId);
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		OrderDetailModel orderDetail = myOrderService.getOrderDetail(myOrderQuery);
		if(orderDetail != null) {
			String memberCode = orderDetail.getMemberCode();
			//不是自己的订单无权查看
			if(!String.valueOf(memberId).equals(memberCode)) {
				return null;
			}
		}
		return orderDetail;
	}
	
	/**
	 * createUserId 前台可以给任意一个非空的整数值
	 * @param createOrder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public TbOrder createModel(@RequestBody @Valid WxCreateOrder createOrder) throws Exception {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		createOrder.setCreateUserId(memberId);
        return orderService.createWxOrder(createOrder);
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Boolean confirmOrder(@RequestParam("id") Integer orderId) throws Exception {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		return myOrderService.confirmReceiveOrder(orderId, memberId);
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public Boolean cancelOrder(@RequestParam("id") Integer orderId) throws Exception {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
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
	public Map<String, Integer> getOrderStatistic() {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
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
