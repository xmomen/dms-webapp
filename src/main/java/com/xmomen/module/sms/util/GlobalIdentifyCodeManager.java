package com.xmomen.module.sms.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xmomen.module.sms.model.IdentifyCodeModel;

public class GlobalIdentifyCodeManager {

	static Map<String, IdentifyCodeModel> identifyCodeCache = new ConcurrentHashMap<String, IdentifyCodeModel>();
	
	public static IdentifyCodeModel getIdentifyCode(String identifyCodeKey) {
		return identifyCodeCache.get(identifyCodeKey);
	}
	
	public static IdentifyCodeModel updateIdenfifyCode(String identifyCodeKey, String identifyCode) {
		IdentifyCodeModel identifyCodeModel = new IdentifyCodeModel(identifyCode);
		identifyCodeCache.put(identifyCodeKey, identifyCodeModel);
		return identifyCodeModel;
	}
}
