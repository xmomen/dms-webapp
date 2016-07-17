package com.xmomen.module.base.util;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * IHttpRequest.java 2014年11月6日下午6:42:07
 * 
 * 
 * Copyright (c) 2014 by MTA.
 * 
 * @author jizong.li
 * @Email 85150225@qq.com
 * @company 上海递优国际物流
 * @description
 * @version 1.0
 */
public interface HttpRequest {

	/**
	 * get 请求 返回 string
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public String doGet(String url,Map<String, String> paramsMap);
	
	/**
	 * get 请求 返回jsonobject 无参
	 * @param url
	 * @return
	 */
	public JSONObject doGetToJsonObject(String url);
	
	/**
	 * do get 请求 返回 jsonobject 有参
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public JSONObject doGetToJsonObject(String url,Map<String,String> paramsMap);
	
	/**
	 * do get 请求 返回 string 无参
	 * @param url
	 * @return
	 */
	public String doGet(String url);
	
	/**
	 * 
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public String doPost(String url,Map<String, String> paramsMap);
	
	public String doPost(String url, String jsonXMLString);
	
	public String doPost(String url);
	
	public JSONObject doPostToJsonObject(String url,Map<String,String> paramsMap);
}
