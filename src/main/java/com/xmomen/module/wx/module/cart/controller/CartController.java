package com.xmomen.module.wx.module.cart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.wx.module.cart.model.UpdateCartModel;
import com.xmomen.module.wx.module.cart.service.CartService;

@Controller
@RequestMapping(value = "/wx")
public class CartController {

	@Autowired
	private CartService cartService;

	@ResponseBody
	@RequestMapping(value ="/cart", method = RequestMethod.GET)
	public List<ProductModel> getCartProduct(@RequestParam(value = "memberId", required = true) Integer memberId) {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setMemberCode(String.valueOf(memberId));
		return cartService.getProductsInCart(productQuery);
	}
	
	@ResponseBody
	@RequestMapping(value ="/cart", method = RequestMethod.POST)
	public Boolean updateCart(@RequestBody @Valid UpdateCartModel updateCartModel){
		cartService.change(String.valueOf(updateCartModel.getMemberId()), updateCartModel.getItemId(), updateCartModel.getItemQty());
		return Boolean.TRUE;
	}

	@ResponseBody
	@RequestMapping(value = "/cart/sync", method = RequestMethod.GET)
	public Boolean syncCart(@RequestParam(value = "memberId", required = true) Integer memberId) {
		cartService.syncToDB(String.valueOf(memberId));
		return Boolean.TRUE;
	}
}
