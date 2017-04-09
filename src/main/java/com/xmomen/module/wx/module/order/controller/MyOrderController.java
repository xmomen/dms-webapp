package com.xmomen.module.wx.module.order.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.model.WxCreateOrder;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.model.OrderProductItem;
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
		myOrderQuery.setUserId(Integer.valueOf(memberId));
		return myOrderService.myOrder(myOrderQuery);
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public OrderDetailModel orderDetail(@PathVariable("orderId") Integer orderId) {
		MyOrderQuery myOrderQuery  = new MyOrderQuery();
		myOrderQuery.setOrderId(orderId);
		return myOrderService.getOrderDetail(myOrderQuery);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public TbOrder createModel(@RequestBody @Valid WxCreateOrder createOrder){
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
        createOrder.setCreateUserId(userId);
        return orderService.createWxOrder(createOrder);
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Boolean confirmOrder(@RequestParam("id") Integer orderId, @RequestParam("memberId") Integer memberId) throws ArgumentValidException {
        return myOrderService.confirmReceiveOrder(orderId, memberId);
	}
	
	@RequestMapping(value = "/wx/order/pay", method = RequestMethod.POST)
	@ResponseBody
	public Boolean payOrder(@RequestBody @Valid PayOrderModel payOrderModel, BindingResult bindingResult) throws ArgumentValidException {
		if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
		return orderService.payWxOrder(payOrderModel);
	}
	
	@RequestMapping(value = "/wx/coupon", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductModel> getCouponItems(@RequestParam("couponNo") String couponNo) {
		return orderService.getCouponItems(couponNo);
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
