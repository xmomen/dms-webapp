package com.xmomen.module.wb.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.wx.module.cart.model.UpdateCartModel;
import com.xmomen.module.wx.module.cart.service.CartService;

@RestController
@RequestMapping(value = "/wb")
public class PcCartController {

	@Autowired
	private CartService cartService;

	@ResponseBody
	@RequestMapping(value ="/cart", method = RequestMethod.GET)
	public List<ProductModel> getCartProduct() {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		ProductQuery productQuery = new ProductQuery();
		productQuery.setMemberCode(String.valueOf(memberId));
		return cartService.getProductsInCart(productQuery);
	}
	
	@ResponseBody
	@RequestMapping(value ="/cart", method = RequestMethod.POST)
	public Boolean updateCart(@RequestBody @Valid UpdateCartModel updateCartModel){
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		cartService.change(String.valueOf(memberId), updateCartModel.getItemId(), updateCartModel.getItemQty());
		return Boolean.TRUE;
	}

	@ResponseBody
	@RequestMapping(value = "/cart/sync", method = RequestMethod.GET)
	public Boolean syncCart() {
		Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		cartService.syncToDB(String.valueOf(memberId));
		return Boolean.TRUE;
	}
}
