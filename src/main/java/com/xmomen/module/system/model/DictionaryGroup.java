package com.xmomen.module.system.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 16/4/2.
 */
public class DictionaryGroup implements Serializable {

    /**
     * 物理主键
     */
    private Integer id;
    /**
     * 字典编号
     */
    private String dictionaryCode;

    /**
     * 字典描述
     */
    private String dictionaryDesc;

    /**
     * 是否启用
     */
    private Integer available;

    /**
     * 启用状态描述
     */
    private String availableDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryDesc() {
        return dictionaryDesc;
    }

    public void setDictionaryDesc(String dictionaryDesc) {
        this.dictionaryDesc = dictionaryDesc;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getAvailableDesc() {
        return availableDesc;
    }

    public void setAvailableDesc(String availableDesc) {
        this.availableDesc = availableDesc;
    }
}
