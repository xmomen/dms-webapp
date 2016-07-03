package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_express")
public class CdExpress extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 快递编号
     */
    private String expressCode;

    /**
     * 快递公司名
     */
    private String expressName;

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

    @Column(name = "EXPRESS_CODE")
    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
        if(expressCode == null){
              removeValidField("expressCode");
              return;
        }
        addValidField("expressCode");
    }

    @Column(name = "EXPRESS_NAME")
    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
        if(expressName == null){
              removeValidField("expressName");
              return;
        }
        addValidField("expressName");
    }
}