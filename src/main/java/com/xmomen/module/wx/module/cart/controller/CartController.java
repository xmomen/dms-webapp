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
public class CartController {

	@Autowired
	private CartService cartService;

	@ResponseBody
	@RequestMapping(value ="/cart", method = RequestMethod.GET)
	public List<ProductModel> getCartProduct(@RequestParam(value = "memberCode", required = true) String userOpenId) {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setMemberCode(userOpenId);
		return cartService.getProductsInCart(productQuery);
	}
	
	@ResponseBody
	@RequestMapping(value ="/cart", method = RequestMethod.POST)
	public Boolean updateCart(@RequestBody @Valid UpdateCartModel updateCartModel, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
		cartService.change(updateCartModel.getUserToken(), updateCartModel.getItemId(), updateCartModel.getItemNumber());
		return Boolean.TRUE;
	}
}
