package com.xmomen.module.wb.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public @Data class PcUpdatePasswordModel {

	@NotNull(message = "手机号码必填")
	@NotBlank(message = "手机号码必填")
	private String phoneNumber;
	
	@NotNull(message = "密码必填")
	@NotBlank(message = "密码必填")
	private String password;
	
	@NotNull(message = "旧密码必填")
	@NotBlank(message = "旧密码必填")
	private String oldPassword;
}
