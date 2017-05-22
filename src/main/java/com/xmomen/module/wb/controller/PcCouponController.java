package com.xmomen.module.wb.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.wx.module.coupon.model.CouponQueryModel;
import com.xmomen.module.wx.module.coupon.model.WxCouponModel;

@RestController
@RequestMapping("/wb/coupon")
public class PcCouponController {

	@Autowired
	private CouponService couponService;

	@RequestMapping(method = RequestMethod.GET)
	List<WxCouponModel> getCoupons(@RequestParam(value="couponType") Integer couponType,
			@RequestParam(value="useable", required = false) Boolean useable) {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		CouponQueryModel couponQueryModel = new CouponQueryModel();
		couponQueryModel.setCdUserId(memberId);
		couponQueryModel.setCouponType(couponType);
		couponQueryModel.setUseable(useable);
		return couponService.getMyCouponList(couponQueryModel);
	}
	
	@RequestMapping(value="/bind", method = RequestMethod.POST)
	Boolean bindCoupon(@RequestParam(value="couponNumber", required=true) String couponNumber,
			@RequestParam(value="password", required = false) String password) throws Exception {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		return couponService.bindMember(couponNumber, memberId, password);
	}
	
	@RequestMapping(value="/validate", method = RequestMethod.GET)
	Boolean validateCoupon(@RequestParam(value="couponNumber", required=true) String couponNumber,
			@RequestParam(value="password", required = false) String password) {
		return couponService.validate(couponNumber, password);
	}
	
	@RequestMapping(value="/reset", method = RequestMethod.POST)
	Boolean resetPasword(@RequestParam(value="couponNumber", required=true) String couponNumber,
			@RequestParam(value="password", required = false) String password,
			@RequestParam(value="newPassword", required = true) String newPassword) throws Exception {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		return couponService.resetPasword(couponNumber, password, newPassword, memberId);
	}
}
