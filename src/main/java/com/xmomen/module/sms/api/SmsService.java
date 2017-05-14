package com.xmomen.module.sms.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.sms.util.GlobalIdentifyCodeManager;
import com.xmomen.module.sms.util.HttpUtil;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;
import com.xmomen.module.wx.util.JsonUtils;

public class SmsService {

	static Logger log = LoggerFactory.getLogger(SmsService.class);

	private String appCode;
	private String signName;
	private String templateCode;
	/**
	 * 
	 * @param phoneNumber
	 * @return 如果为空代表调用API返回失败
	 */
	public SmsResponse sendSingleRequest(String phoneNumber) {
		String host = "http://sms.market.alicloudapi.com";
		String path = "/singleSendSms";
		String method = "GET";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appCode);
		Map<String, String> querys = new HashMap<String, String>();
		// 生成identifyCode
		String identifyCode = RandomStringGenerator.getRandomNumberStrByLength(4);
		// ParamString = {"no":"123456"}
		String paramString = "{'no': '" + identifyCode + "'}";
		querys.put("ParamString", paramString);
		querys.put("RecNum", phoneNumber);
		GlobalIdentifyCodeManager.updateIdenfifyCode(phoneNumber, identifyCode);
		//签名的名字
		querys.put("SignName", signName);
		//模板Code
		querys.put("TemplateCode", templateCode);
		try {
			HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
			if(response == null) {
				log.error("未收到response");
				return null;
			} else {
				int statusCode = response.getStatusLine().getStatusCode();
				String result = EntityUtils.toString(response.getEntity());
				log.error("收到的回复:" + result);
				if(200 == statusCode) {
					if (StringUtilsExt.isNotEmpty(result)) {
		                return JsonUtils.parseJSON(StringEscapeUtils.unescapeJson(result), SmsResponse.class);
		            }
				} else {
					throw new BusinessException("返回状态码:" + statusCode);
				}
			}
		} catch (Exception e) {
			log.error("调用SMS API失败", e);
			return null;
		}
		return null;
	}
	
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
