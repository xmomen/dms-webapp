package com.xmomen.module.wx.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdBind;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderItem;
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
				callbackUrl = reqServer+"/bind/auth2Url?url=" + URLEncoder.encode(url,"UTF-8") + "&param=" + param;
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
		logger.info("openid----->" + openId);
		//查询是否有绑定
		CdBind bind = new CdBind();
		bind.setOpenId(openId);
		List<CdBind> binds = mybatisDao.selectByModel(bind);
		request.setAttribute("openId", openId);
		if(binds != null && binds.size() > 0){
			bind = binds.get(0);
			//跳转到收货页面
			if(url.equals("/wx/receipt")){
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
}
