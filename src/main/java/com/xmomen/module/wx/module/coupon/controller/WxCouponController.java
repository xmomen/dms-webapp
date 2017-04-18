package com.xmomen.module.wx.module.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.wx.module.coupon.model.CouponQueryModel;
import com.xmomen.module.wx.module.coupon.model.WxCouponModel;


@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController {

	@Autowired
	private CouponService couponService;

	@RequestMapping(method = RequestMethod.GET)
	List<WxCouponModel> getCoupons(@RequestParam(value = "memberId") Integer memberId, 
			@RequestParam(value="couponType") Integer couponType,
			@RequestParam(value="useable", required = false) Boolean useable) {
		CouponQueryModel couponQueryModel = new CouponQueryModel();
		couponQueryModel.setCdUserId(memberId);
		couponQueryModel.setCouponType(couponType);
		couponQueryModel.setUseable(useable);
		return couponService.getMyCouponList(couponQueryModel);
	}
	
	@RequestMapping(value="/bind", method = RequestMethod.POST)
	Boolean bindCoupon(@RequestParam(value = "memberId", required=true) Integer memberId,
			@RequestParam(value="couponNumber", required=true) String couponNumber,
			@RequestParam(value="password", required = false) String password) throws Exception {
		return couponService.bindMember(couponNumber, memberId, password);
	}
	
	@RequestMapping(value="/validate", method = RequestMethod.GET)
	Boolean validateCoupon(@RequestParam(value="couponNumber", required=true) String couponNumber,
			@RequestParam(value="password", required = false) String password) {
		return couponService.validate(couponNumber, password);
	}
	
	@RequestMapping(value="/reset", method = RequestMethod.POST)
	Boolean resetPasword(@RequestParam(value = "memberId", required=true) Integer memberId,
			@RequestParam(value="couponNumber", required=true) String couponNumber,
			@RequestParam(value="password", required = false) String password,
			@RequestParam(value="newPassword", required = true) String newPassword) throws Exception {
		return couponService.resetPasword(couponNumber, password, newPassword, memberId);
	}
}
