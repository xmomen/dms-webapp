package com.xmomen.module.wx.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.ReturnOrder;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdBind;
import com.xmomen.module.base.entity.CdExpressMember;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.entity.TbOrderRef;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.receipt.entity.TbReceivingCodeRequest;
import com.xmomen.module.wx.model.AccessTokenOAuth;
import com.xmomen.module.wx.service.BindService;
import com.xmomen.module.wx.util.Auth2Handler;
import com.xmomen.module.wx.util.PropertiesUtils;
/**
 * 微信绑定控制器
 * @author Administrator
 *
 */

@Controller
public class BindController {
	Logger logger = LoggerFactory.getLogger(BindController.class);
	
	@Autowired
	BindService bindService;
	
    @Autowired
    MybatisDao mybatisDao;
	
	@RequestMapping(value="/bind/auth")
	public String oauth2Api(HttpServletRequest request,HttpServletResponse response,@RequestParam("url") String url,
								@RequestParam(value="param",required=false) String param) {
		String redirectUrl = "";
		logger.info("web url <-->" + request.getRequestURL() +"?"+  request.getQueryString());
		logger.info("r <-->" +url);
		if(StringUtilsExt.isNotEmpty(url)) {
			String callbackUrl;
			try {
				String reqServer = PropertiesUtils.findPropertiesKey("wx.domain");
				callbackUrl = "http://www.j9soft.com/bind/auth2Url?url=" + URLEncoder.encode(url,"UTF-8") + "&param=" + param;
				logger.info("oauth callbackurl <--->" + callbackUrl);
				redirectUrl = Auth2Handler.getOauthUrl(callbackUrl);
				logger.info("oauth redirectUrl<---->" + redirectUrl);
				response.sendRedirect(redirectUrl);
			} catch (UnsupportedEncodingException e) {
				logger.error("r参数encode失败：" + url,e);
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("oauth redirect跳转失败：",e);
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	
	@RequestMapping(value="/bind/auth2Url")
	public String oauth2Url(HttpServletRequest request,HttpServletResponse response,@RequestParam("code") String code,
							@RequestParam("url") String url,@RequestParam(value="param",required=false) String param) throws IOException {
		AccessTokenOAuth accessToken = Auth2Handler.getAccessToken(code);
		String openId = accessToken.getOpenid();
//		String openId = "ovH3uwhNwt_Q646pRA43aCCwtSRE";
		logger.info("openid----->" + openId);
		//查询是否有绑定
		CdBind bind = new CdBind();
		bind.setOpenId(openId);
		List<CdBind> binds = mybatisDao.selectByModel(bind);
		request.setAttribute("openId", openId);
		if(binds != null && binds.size() > 0){
			bind = binds.get(0);
			String phone = bind.getPhone();
			//跳转到收货页面
			if(url.equals("/wx/receipt")){
				request.setAttribute("express", "0");
				//订单信息
				TbOrder order = new TbOrder();
				order.setOrderNo(param);
				order = mybatisDao.selectOneByModel(order);
				request.setAttribute("orderInfo", order);
				//订单明细信息
				TbOrderItem orderItem = new TbOrderItem();
				orderItem.setOrderNo(param);
				List<TbOrderItem> orderItems = mybatisDao.selectByModel(orderItem);
				request.setAttribute("orderItemInfo", orderItems);
				request.setAttribute("phone", phone);
				if(order.getConsigneePhone().equals(phone)){
					return url;
				}
				//查询扫描的是不是快递员 如果是快递员 则需要输入收货码
				//查找快递员信息
				CdExpressMember expressMember = new CdExpressMember();
				expressMember.setPhone(phone);
				List<CdExpressMember> expressMembers = mybatisDao.selectByModel(expressMember);
				if(expressMembers != null && expressMembers.size() > 0){
					//快递员扫描
					request.setAttribute("express", "1");
				}else{
					String message = "您绑定手机号和订单收货人手机号不一致，不能收货，请确认";
					request.setAttribute("message", message);
					return "wx/receiptNoAuth";
				}
				return url;
			}
			//扫码送货
			else if(url.equals("/wx/scanning")){
				String message = bindService.bindExpressMember(bind.getPhone(), param);
				request.setAttribute("message", message);
				if("扫描成功".equals(message)){
					return "wx/scanningSuccess";
				}else{
					return "wx/scanningFail";
				}
			}
		}
		//跳转到绑定页面
		else{
			request.setAttribute("message", "请先绑定手机号，再进行操作!");
			return "wx/bind";
		}
		return null;
	}
	
	/**
	 * 绑定页面跳转
	 * 
	 * @param openId 微信唯一标识
	 * @param bindType 绑定类型
	 */
	@RequestMapping(value = "/wx/bind", method = RequestMethod.GET)
    public String bind(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="openId") String openId,
    		@RequestParam(value="bindType") String bindType
    		){
    		request.setAttribute("openId", openId);
    		request.setAttribute("bindType", bindType);
    		return "wx/bind";
    }
	
	
	/**
	 * 账号绑定
	 * 
	 * @param openId 微信唯一标识
	 * @param phone 手机号
	 */
	@RequestMapping(value = "/bind/account", method = RequestMethod.GET)
    public String bindAccount(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="openId") String openId,
    		@RequestParam(value="phone") String phone,
    		@RequestParam(value="bindType") String bindType
    		){
    	AssertExt.notNull(openId, "openId不能为空");
    	AssertExt.notNull(phone, "手机号不能为空");
    	boolean flag = bindService.bindAccount(openId,phone,bindType);
    	if(flag){
    		request.setAttribute("phone", phone);
    		return "wx/bindSuccess";
    	}
    	else {
    		request.setAttribute("openId", openId);
    		request.setAttribute("bindType", bindType);
    		return "wx/bindFail";
    	}
    }
	
	
	/**
	 * 扫描发运
	 * 
	 * @param openId 微信唯一标识
	 * @param bindType 绑定类型
	 */
	@RequestMapping(value = "/bind/scanning", method = RequestMethod.GET)
	@ResponseBody
    public String scanning(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="phone") String phone,
    		@RequestParam(value="orderNo") String orderNo
    		){
	  		AssertExt.notNull(orderNo, "订单号不能为空");
	  		AssertExt.notNull(phone, "手机号不能为空");
	  		bindService.bindExpressMember(phone, orderNo);
    		return "wx/scanningSuccess";
    }
	
	/**
	 * 收货
	 */
	@RequestMapping(value="/wx/shouhuo",method = RequestMethod.GET)
	@ResponseBody
	public boolean shouhuo(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="shouhuoNo",required=false) String shouhuoNo,
    		@RequestParam(value="openId") String openId,
    		@RequestParam(value="orderNo") String orderNo
    		){
		//如果有收货码 判断收货码是否正确
		TbOrderRef orderRef = new TbOrderRef();
		orderRef.setOrderNo(orderNo);
		orderRef.setRefType("SHOU_HUO_NO");
		orderRef = mybatisDao.selectOneByModel(orderRef);
		//判断输入的收货码是否正确
		if(StringUtilsExt.isBlank(shouhuoNo) || (StringUtilsExt.isNotBlank(shouhuoNo) && shouhuoNo.equals(orderRef.getRefValue()))){
			return this.bindService.orderShouhuo(openId, orderNo,shouhuoNo);
		}else{
			return false;
		}
	}
	
	/**
	 * 收货码请求
	 */
	@RequestMapping(value="/wx/shouhuoRequest",method = RequestMethod.GET)
	@ResponseBody
	public boolean shouhuoRequest(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="phone") String phone,
    		@RequestParam(value="openId") String openId,
    		@RequestParam(value="orderNo") String orderNo
    		){
		logger.info("请求的订单编号：",orderNo);
		TbReceivingCodeRequest receivingCodeRequest = new TbReceivingCodeRequest();
		receivingCodeRequest.setOrderNo(orderNo);
		receivingCodeRequest.setRequestPhone(phone);
		CdExpressMember expressMember = new CdExpressMember();
		expressMember.setPhone(phone);
		List<CdExpressMember> expressMembers = mybatisDao.selectByModel(expressMember);
		if(expressMembers != null && expressMembers.size() > 0){
			CdExpressMember expressMember2 = expressMembers.get(0);
			receivingCodeRequest.setRequestUser(expressMember2.getMemberName());
			receivingCodeRequest.setRequestTime(new Date());
			receivingCodeRequest.setRequestExpressId(expressMember2.getCdExpressId());
		}
		this.mybatisDao.save(receivingCodeRequest);
		return true;
	}

	
	/**
	 * 二次配送
	 */
	@RequestMapping(value="/wx/twoPeiSong",method = RequestMethod.GET)
	@ResponseBody
	public boolean twoPeiSong(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="phone") String phone,
    		@RequestParam(value="orderNo") String orderNo
    		){
		return true;
	}
	@Autowired
	OrderService orderService;

	/**
	 * 新增部分退货订单
	 * @param returnOrder
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/order/return", method = RequestMethod.POST)
	@Log(actionName = "微信回调接口－订单部分退货")
	public void returnOrder(@RequestBody @Valid ReturnOrder returnOrder, BindingResult bindingResult) throws ArgumentValidException {
		if(bindingResult != null && bindingResult.hasErrors()){
			throw new ArgumentValidException(bindingResult);
		}
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
		orderService.returnOrder(returnOrder);
	}
}
