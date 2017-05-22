package com.xmomen.module.sms.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.sms.constant.Constants;
import com.xmomen.module.sms.constant.HttpSchema;
import com.xmomen.module.sms.enums.Method;
import com.xmomen.module.sms.util.GlobalIdentifyCodeManager;
import com.xmomen.module.sms.util.HttpUtil;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;
import com.xmomen.module.wx.util.JsonUtils;

public class SmsService {

	static Logger log = LoggerFactory.getLogger(SmsService.class);

	private String signName;
	private String templateCode;
	private String appKey;
	private String appSecret;
	
	private final static String ERRORKEY = "errorMessage";  //返回错误的key

	/**
	 * 
	 * @param phoneNumber
	 * @return 如果为空代表调用API返回失败
	 */
	public SmsResponse sendSingleRequest(String phoneNumber) {
		String host = "sms.market.alicloudapi.com";
		String path = "/singleSendSms";
		System.out.println("appKey is:" + appKey);
		System.out.println("appSecret is:" + appSecret);
		Request request =  new Request(Method.GET, HttpSchema.HTTP + host, path, appKey, appSecret, Constants.DEFAULT_TIMEOUT);
		
		Map<String, String> querys = new HashMap<String, String>();
		// 生成identifyCode
		String identifyCode = RandomStringGenerator.getRandomNumberStrByLength(4);
		// ParamString = {"no":"123456"}
		String paramString = "{'code': '" + identifyCode + "'}";
		querys.put("ParamString", paramString);
		querys.put("RecNum", phoneNumber);
		//签名的名字
		querys.put("SignName", signName);
		//模板Code
		querys.put("TemplateCode", templateCode);
		request.setQuerys(querys);
		try {
			Map<String, String> bodymap = new HashMap<String, String>();
			Response response = Client.execute(request);
			if(response == null) {
				log.error("未收到response");
				return null;
			} else {
				int statusCode = response.getStatusCode();
				String result = response.getBody();
				log.error("收到的回复:" + result);
				if(200 == statusCode) {
					bodymap = readResponseBodyContent(result);
					if(null != bodymap.get(ERRORKEY)) {
						throw new BusinessException(bodymap.get(ERRORKEY));
					} else {
						SmsResponse smsResponse = new SmsResponse();
						if ("true".equals(bodymap.get("success"))) {
							smsResponse.setSuccess(true);
							log.info("identifyCode is:" + identifyCode);
							GlobalIdentifyCodeManager.updateIdenfifyCode(phoneNumber, identifyCode);
							return smsResponse;
			            }
					}
				} else {
					throw new BusinessException("返回状态码:" + statusCode + ",错误信息:" + response.getErrorMessage());
				}
			}
		} catch (Exception e) {
			log.error("调用SMS API失败", e);
			return null;
		}
		return null;
	}
	private Map<String, String> readResponseBodyContent(String body) {
        Map<String, String> map = new HashMap<String, String>();    
        try {
            JSONObject jsonObject = JSON.parseObject(body);
            if (null != jsonObject) {               
                for(Entry<String, Object> entry : jsonObject.entrySet()){
                    map.put(entry.getKey(), entry.getValue().toString());
                }               
            }
            if ("false".equals(map.get("success"))) {
                map.put(ERRORKEY, map.get("message"));
            }
        } catch (Exception e) {
            map.put(ERRORKEY, body);
        }
        return map;
    }

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
