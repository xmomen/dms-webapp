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
@Table(name = "cd_item_contract_item")
public class CdItemContractItem extends BaseMybatisModel {
    /**
     * 
     */
    private Integer cdItemContractItemId;

    /**
     * 产品合同
     */
    private Integer cdContractId;

    /**
     * 当合同适用范围为1的时候，每种产品要设定对应的合同价
     */
    private Integer cdItemId;

    /**
     * 合同价格
     */
    private BigDecimal contractPrice;

    @Column(name = "CD_ITEM_CONTRACT_ITEM_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getCdItemContractItemId() {
        return cdItemContractItemId;
    }

    public void setCdItemContractItemId(Integer cdItemContractItemId) {
        this.cdItemContractItemId = cdItemContractItemId;
        if(cdItemContractItemId == null){
              removeValidField("cdItemContractItemId");
              return;
        }
        addValidField("cdItemContractItemId");
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

    @Column(name = "CONTRACT_PRICE")
    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
        if(contractPrice == null){
              removeValidField("contractPrice");
              return;
        }
        addValidField("contractPrice");
    }
}