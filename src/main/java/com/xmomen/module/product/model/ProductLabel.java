package com.xmomen.module.product.model;

public enum ProductLabel {

	XIAN_SHI_QIANG_GOU("xianShiQiangGou", "XIAN_SHI_QIANG_GOU"),
	XIN_PIN_CHANG_XIAN("xinPinChangXian", "XIN_PIN_CHANG_XIAN"),
	RE_MAI_TUI_JIAN("reMaiTuiJian", "RE_MAI_TUI_JIAN");
	String desc;
	String entityField;
	public String getEntityField() {
		return this.entityField;
	}
	public String getDesc() {
		return this.desc;
	}
	ProductLabel(String desc, String entityField) {
		this.desc = desc;
		this.entityField = entityField;
	};
	
	public static ProductLabel enumOf(String desc) {
		ProductLabel[] labels = ProductLabel.values();
		int length = labels.length;
		for(int i = 0; i < length; i++) {
			ProductLabel label = labels[i];
			if(label.desc.equalsIgnoreCase(desc)) return label;
		}
		return null;
	}
}
