package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_contract_item")
public class CdContractItem extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 产品合同
     */
    private Integer cdContractId;

    /**
     * 优惠方式
     */
    private Integer contractType;

    /**
     * 当合同适用范围为1的时候，每种产品要设定对应的合同价
     */
    private Integer cdItemId;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 合同价格
     */
    private BigDecimal contractValue;

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

    @Column(name = "CD_CONTRACT_ID")
    public Integer getCdContractId() {
        return cdContractId;
    }

    public void setCdContractId(Integer cdContractId) {
        this.cdContractId = cdContractId;
        if(cdContractId == null){
              removeValidField("cdContractId");
              return;
        }
        addValidField("cdContractId");
    }

    @Column(name = "CONTRACT_TYPE")
    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
        if(contractType == null){
              removeValidField("contractType");
              return;
        }
        addValidField("contractType");
    }

    @Column(name = "CD_ITEM_ID")
    public Integer getCdItemId() {
        return cdItemId;
    }

    public void setCdItemId(Integer cdItemId) {
        this.cdItemId = cdItemId;
        if(cdItemId == null){
              removeValidField("cdItemId");
              return;
        }
        addValidField("cdItemId");
    }

    @Column(name = "DISCOUNT")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
        if(discount == null){
              removeValidField("discount");
              return;
        }
        addValidField("discount");
    }

    @Column(name = "CONTRACT_VALUE")
    public BigDecimal getContractValue() {
        return contractValue;
    }

    public void setContractValue(BigDecimal contractValue) {
        this.contractValue = contractValue;
        if(contractValue == null){
              removeValidField("contractValue");
              return;
        }
        addValidField("contractValue");
    }
}