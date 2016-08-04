package com.xmomen.module.wx.model;
/**
 * AccessTokenOAuth.java 2014年10月27日下午6:12:55
 * 
 * 
 * Copyright (c) 2014 by MTA.
 * 
 * @author jizong.li
 * @Email 85150225@qq.com
 * @company 上海递优国际物流
 * @description 网页授权接口调用凭证 OAuth2.0
 * @version 1.0
 */
public class AccessTokenOAuth {

	private String accessToken;
	
	private int expiresIn;
	
	private String refreshToken;
	
	private String openid;
	
	private String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
}
