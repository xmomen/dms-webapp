package com.xmomen.module.wx.constants;
/**   
 * @Title: AuthorizeScope.java 
 * @Package com.udf.wechat.enums 
 * @Description: TODO(应用授权作用域) 
 * @author jizong.li 85150225@qq.com   
 * @date 2014年10月11日 下午8:05:56 
 * @version V1.0   
 */
public enum AuthorizeScope {

	//不弹出授权页面，直接跳转，只能获取用户openid
	snsapi_base,
	
	//弹出授权页面，可通过openid拿到昵称、性别、所在地、
	snsapi_userinfo;
}
