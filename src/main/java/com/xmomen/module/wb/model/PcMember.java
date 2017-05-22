package com.xmomen.module.wb.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public @Data class PcMember {

	//昵称,可以为空
	private String name;
	
	@NotNull(message = "手机号码必填")
	@NotBlank(message = "手机号码必填")
	private String phoneNumber;
	
	//主要用于密码重置
	//@NotNull
	private String email;
	
	//客户密码
	@NotNull(message = "密码必填")
	@NotBlank(message = "密码必填")
	private String password;
	
	//手机验证码
	@NotNull(message="手机验证码必填")
	@NotBlank(message="手机验证码必填")
	private String phoneIdentifyCode;
}
