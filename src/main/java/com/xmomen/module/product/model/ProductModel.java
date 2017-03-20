package com.xmomen.module.product.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public @Data class ProductModel {

	private Integer id;
	private String itemCode;
	private Integer categoryId;
	private String categoryName;
	private String itemDescribe;
	private Integer itemType;
	private String yieldly;
	private String spec;
	private Double basePrice;
	private Double memberPrice;
	private String priceManner;
	private Boolean sellStatus;
	private String sellUnit;
	private Double sellPrice;
	
	private Boolean xianShiXianGou;
	private Boolean xinPinChangXian;
	private Boolean reMaiTuiJian;
	
	@JsonIgnore
	private String picUrl;
	
	private List<String> picUrls;
	private String detailContent;
}
