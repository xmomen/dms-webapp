package com.xmomen.module.pick.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

public @Data 
class PickVo implements Serializable{
	private BigDecimal pickWeight;
	private BigDecimal pickPrice;
	private Integer pickPayType;
	private BigDecimal pickCasePrice;
	private String couponNo;
}
