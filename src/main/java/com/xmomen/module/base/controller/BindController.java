package com.xmomen.module.base.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.service.BindService;
import com.xmomen.module.logger.Log;
/**
 * 微信绑定控制器
 * @author Administrator
 *
 */

@RestController
public class BindController {
	
	@Autowired
	BindService bindService;
	
	/**
	 * 账号绑定
	 * 
	 * @param openId 微信唯一标识
	 * @param phone 手机号
	 */
	@RequestMapping(value = "/bind/account", method = RequestMethod.GET)
    @Log(actionName ="账号绑定")
    public boolean bindAccount(
    		@RequestParam(value="openId") String openId,
    		@RequestParam(value="phone") String phone
    		){
    	AssertExt.notNull("openId", "openId不能为空");
    	AssertExt.notNull("phone", "手机号不能为空");
    	return bindService.bindAccount(openId,phone);
    }
     
	//收货
	
}
