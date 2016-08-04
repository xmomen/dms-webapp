package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

public @Data class CreatePlan implements Serializable {
	/**
     * 主键
     */
    private Integer id;

    /**
     * 计划名称
     */
    @NotBlank
    private String planName;

    /**
     * 计划创建人
     */
    
    private String createUser;

    /**
     * 计划创建时间
     */
    private Date createTime;

    /**
     * 配送频率
     */
    @NotNull
    private Integer deliveryType;

    /**
     * 配送时间(一周的星期几）
     */
    @NotBlank
    private String deliveryTime;

    /**
     * 配送的次数
     */
    @NotNull
    private Integer deliverCount;
    

    private BigDecimal price;
    
    private List<PlanItemModel> planItems;

}
