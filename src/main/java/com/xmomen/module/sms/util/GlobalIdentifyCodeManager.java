package com.xmomen.module.sms.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.xmomen.module.sms.model.IdentifyCodeModel;

public class GlobalIdentifyCodeManager {

	static Map<String, IdentifyCodeModel> identifyCodeCache = new ConcurrentHashMap<String, IdentifyCodeModel>();
	//static Map<String, IdentifyCodeModel> operationCodeCache = new ConcurrentHashMap<String, IdentifyCodeModel>();

	public static IdentifyCodeModel getIdentifyCode(String identifyCodeKey) {
		return identifyCodeCache.get(identifyCodeKey);
	}
	
	public static IdentifyCodeModel updateIdenfifyCode(String identifyCodeKey, String identifyCode) {
		IdentifyCodeModel identifyCodeModel = new IdentifyCodeModel(identifyCode, 15L, TimeUnit.MINUTES);
		identifyCodeCache.put(identifyCodeKey, identifyCodeModel);
		return identifyCodeModel;
	}
	
	
	/*public static IdentifyCodeModel getOperationCode(String operationCodeKey) {
		return operationCodeCache.get(operationCodeKey);
	}
	
	public static IdentifyCodeModel updateOperationCode(String operationCodeKey, String identifyCode) {
		IdentifyCodeModel identifyCodeModel = new IdentifyCodeModel(identifyCode, 15L, TimeUnit.MINUTES);
		operationCodeCache.put(operationCodeKey, identifyCodeModel);
		return identifyCodeModel;
	}*/
}
