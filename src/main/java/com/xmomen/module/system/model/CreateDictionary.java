package com.xmomen.module.system.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 16/4/2.
 */
public class CreateDictionary implements Serializable {

    /**
     * 字典
     */
    @NotNull
    private Integer sysDictionaryId;

    /**
     * 显示值
     */
    @NotNull
    @NotEmpty
    private String showValue;

    /**
     * 实际值
     */
    @NotNull
    @NotEmpty
    private String realValue;

    /**
     * 排位
     */
    private Integer sortValue;

    /**
     * 0-禁用，1-启用
     */
    private Integer available;

    public Integer getSysDictionaryId() {
        return sysDictionaryId;
    }

    public void setSysDictionaryId(Integer sysDictionaryId) {
        this.sysDictionaryId = sysDictionaryId;
    }

    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
    }

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
