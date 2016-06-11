package com.xmomen.module.pick.entity;

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
@Table(name = "tb_recharge_log")
public class TbRechargeLog extends BaseMybatisModel {
    /**
     * 充值记录
     */
    private Integer id;

    /**
     * 充值的卡号
     */
    private String couponNo;

    /**
     * 充值金额
     */
    private BigDecimal rechargePrice;

    /**
     * 充值时间
     */
    private Date rechargeDate;

    /**
     * 充值人
     */
    private Integer rechargeUser;

    /**
     * 充值地点
     */
    private String rechargePlace;

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

    @Column(name = "COUPON_NO")
    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
        if(couponNo == null){
              removeValidField("couponNo");
              return;
        }
        addValidField("couponNo");
    }

    @Column(name = "RECHARGE_PRICE")
    public BigDecimal getRechargePrice() {
        return rechargePrice;
    }

    public void setRechargePrice(BigDecimal rechargePrice) {
        this.rechargePrice = rechargePrice;
        if(rechargePrice == null){
              removeValidField("rechargePrice");
              return;
        }
        addValidField("rechargePrice");
    }

    @Column(name = "RECHARGE_DATE")
    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
        if(rechargeDate == null){
              removeValidField("rechargeDate");
              return;
        }
        addValidField("rechargeDate");
    }

    @Column(name = "RECHARGE_USER")
    public Integer getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(Integer rechargeUser) {
        this.rechargeUser = rechargeUser;
        if(rechargeUser == null){
              removeValidField("rechargeUser");
              return;
        }
        addValidField("rechargeUser");
    }

    @Column(name = "RECHARGE_PLACE")
    public String getRechargePlace() {
        return rechargePlace;
    }

    public void setRechargePlace(String rechargePlace) {
        this.rechargePlace = rechargePlace;
        if(rechargePlace == null){
              removeValidField("rechargePlace");
              return;
        }
        addValidField("rechargePlace");
    }
}