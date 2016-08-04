package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_plan")
public class CdPlan extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 计划名称
     */
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
     * 配送频率（1-一天,2-一周，3-一月）
     */
    private Integer deliveryType;

    /**
     * 配送时间(一周的星期几，一月的几号等）
     */
    private String deliveryTime;

    /**
     * 配送的次数
     */
    private Integer deliverCount;

    /**
     * 金额
     */
    private BigDecimal price;

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

    @Column(name = "PLAN_NAME")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
        if(planName == null){
              removeValidField("planName");
              return;
        }
        addValidField("planName");
    }

    @Column(name = "CREATE_USER")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
        if(createUser == null){
              removeValidField("createUser");
              return;
        }
        addValidField("createUser");
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime == null){
              removeValidField("createTime");
              return;
        }
        addValidField("createTime");
    }

    @Column(name = "DELIVERY_TYPE")
    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
        if(deliveryType == null){
              removeValidField("deliveryType");
              return;
        }
        addValidField("deliveryType");
    }

    @Column(name = "DELIVERY_TIME")
    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
        if(deliveryTime == null){
              removeValidField("deliveryTime");
              return;
        }
        addValidField("deliveryTime");
    }

    @Column(name = "DELIVER_COUNT")
    public Integer getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(Integer deliverCount) {
        this.deliverCount = deliverCount;
        if(deliverCount == null){
              removeValidField("deliverCount");
              return;
        }
        addValidField("deliverCount");
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        if(price == null){
              removeValidField("price");
              return;
        }
        addValidField("price");
    }
}