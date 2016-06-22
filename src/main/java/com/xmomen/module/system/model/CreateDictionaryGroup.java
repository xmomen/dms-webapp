package com.xmomen.module.system.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 16/4/2.
 */
public class CreateDictionaryGroup implements Serializable {

    /**
     * 字典编号
     */
    @NotNull
    @Length(min = 3, max = 20)
    private String dictionaryCode;

    /**
     * 字典描述
     */
    @NotNull
    private String dictionaryDesc;

    /**
     * 是否启用
     */
    private Integer available;

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
}
