package com.xmomen.module.base.model;

import java.io.Serializable;

import lombok.Data;

public @Data class ExpressMemberModel implements Serializable{
	/**
     * 主键
     */
    private Integer id;

    /**
     * 快递商id
     */
    private Integer cdExpressId;

    /**
     * 快递员姓名
     */
    private String memberName;

    /**
     * 电话号码
     */
    private String phone;

    private String expressName;
}
