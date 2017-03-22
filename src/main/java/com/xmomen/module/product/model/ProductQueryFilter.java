package com.xmomen.module.product.model;

/**
 * The enum is used for filter or order
 * @author xiao
 *
 */
public enum ProductQueryFilter {

	PRICE("price", "SELL_PRICE");

	private String desc;
	private String fieldName;
	// Query condition
	private String condition;
	
	
	ProductQueryFilter(String desc, String fieldName) {
		this(desc, fieldName, null);
	}
	ProductQueryFilter(String desc, String fieldName, String condition) {
		this.desc = desc;
		this.fieldName = fieldName;
		this.condition = condition;
	}
	public String getDesc() {
		return this.desc;
	}
	public String getFieldName() {
		return this.fieldName;
	}
	public String getCondition() {
		return this.condition;
	}
	public static ProductQueryFilter enumOf(String desc) {
		ProductQueryFilter[] filters = ProductQueryFilter.values();
		int length = filters.length;
		for(int i = 0; i < length; i++) {
			ProductQueryFilter filter = filters[i];
			if(filter.desc.equalsIgnoreCase(desc)) return filter;
		}
		return null;
	}
}
