package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_dictionary_parameter")
public class SysDictionaryParameter extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 字典
     */
    private Integer sysDictionaryId;

    /**
     * 显示值
     */
    private String showValue;

    /**
     * 实际值
     */
    private String realValue;

    /**
     * 排位
     */
    private Integer sortValue;

    /**
     * 0-禁用，1-启用
     */
    private Integer available;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "SYS_DICTIONARY_ID")
    public Integer getSysDictionaryId() {
        return sysDictionaryId;
    }

    public void setSysDictionaryId(Integer sysDictionaryId) {
        this.sysDictionaryId = sysDictionaryId;
        if(sysDictionaryId == null){
              removeValidField("sysDictionaryId");
              return;
        }
        addValidField("sysDictionaryId");
    }

    @Column(name = "SHOW_VALUE")
    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
        if(showValue == null){
              removeValidField("showValue");
              return;
        }
        addValidField("showValue");
    }

    @Column(name = "REAL_VALUE")
    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
        if(realValue == null){
              removeValidField("realValue");
              return;
        }
        addValidField("realValue");
    }

    @Column(name = "SORT_VALUE")
    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
        if(sortValue == null){
              removeValidField("sortValue");
              return;
        }
        addValidField("sortValue");
    }

    @Column(name = "AVAILABLE")
    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
        if(available == null){
              removeValidField("available");
              return;
        }
        addValidField("available");
    }
}