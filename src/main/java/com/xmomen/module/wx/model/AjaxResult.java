package com.xmomen.module.wx.model;

import java.io.Serializable;

import lombok.Data;

public @Data class AjaxResult implements Serializable {
	private int result;
	private Object content;
	private String message;
}
