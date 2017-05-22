package com.xmomen.module.sms.api;

public class SmsResponse {

	private boolean success;
	private String message;
	public SmsResponse() {}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public boolean getSuccess() {
		return success;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
