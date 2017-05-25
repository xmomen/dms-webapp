package com.xmomen.module.sms.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import lombok.Data;

public @Data class IdentifyCodeModel {

	private String identifyCode;
	private Date createTime;
	private Date expiredTime;
	
	public IdentifyCodeModel(String identifyCode, Long validTime, TimeUnit timeUnit) {
		this.identifyCode = identifyCode;
		Calendar calendar = new GregorianCalendar();
		createTime = calendar.getTime();
		if(validTime != null) {
			if(validTime > 0) {
				switch(timeUnit) {
					case SECONDS: {
						expiredTime = new Date(createTime.getTime() + validTime * 1000);
						break;
					}
					case MINUTES: {
						expiredTime = new Date(createTime.getTime() + validTime * 1000 * 60);
						break;
					}
					default: {
						throw new IllegalArgumentException("不支持其他时间类型");
					}
				}
			} else {
				throw new IllegalArgumentException("不合法有效时间值");
			}
			
		}
	}
	
	public IdentifyCodeModel(String identifyCode, Long validTime) {
		this(identifyCode, validTime, TimeUnit.SECONDS);
	}
	public IdentifyCodeModel(String identifyCode) {
		this(identifyCode, null);
	}

	public boolean isExpired() {
		Date now = new Date();
		if(expiredTime == null) return false;
		if(now.getTime() > expiredTime.getTime()) {
			return true;
		}
		return false;
	}

}
