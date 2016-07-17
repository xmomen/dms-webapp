package com.xmomen.module.base.constant;


public class WechatUrlConstants {
	
	
	/**
	 * 微信授权地址
	 * appid 公众号的唯一标识
	 * redirect_uri 授权后重定向的回调链接地址
	 * scope  应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
	 * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
       	并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
       	state 重定向后会带上state参数，开发者可以填写任意参数值
       	#wechat_redirect 直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
	 */
	public static final String OAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	/**
	 * 获取oauth网页认证的token
	 */
	public static String GET_ACCESS_TOKEN_OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
}
