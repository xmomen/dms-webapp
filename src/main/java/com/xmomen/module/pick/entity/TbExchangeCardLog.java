package com.xmomen.module.pick.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_exchange_card_log")
public class TbExchangeCardLog extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 旧卡ID
     */
    private Integer oldCouponId;

    /**
     * 旧卡卡号
     */
    private String oldCouponNo;

    /**
     * 
     */
    private Integer newCouponId;

    /**
     * 
     */
    private String newCouponNo;

    /**
     * 换卡操作者
     */
    private Integer rechargeUser;

    /**
     * 换卡采摘点
     */
    private Integer rechargePlace;

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

    @Column(name = "OLD_COUPON_ID")
    public Integer getOldCouponId() {
        return oldCouponId;
    }

    public void setOldCouponId(Integer oldCouponId) {
        this.oldCouponId = oldCouponId;
        if(oldCouponId == null){
              removeValidField("oldCouponId");
              return;
        }
        addValidField("oldCouponId");
    }

    @Column(name = "OLD_COUPON_NO")
    public String getOldCouponNo() {
        return oldCouponNo;
    }

    public void setOldCouponNo(String oldCouponNo) {
        this.oldCouponNo = oldCouponNo;
        if(oldCouponNo == null){
              removeValidField("oldCouponNo");
              return;
        }
        addValidField("oldCouponNo");
    }

    @Column(name = "NEW_COUPON_ID")
    public Integer getNewCouponId() {
        return newCouponId;
    }

    public void setNewCouponId(Integer newCouponId) {
        this.newCouponId = newCouponId;
        if(newCouponId == null){
              removeValidField("newCouponId");
              return;
        }
        addValidField("newCouponId");
    }

    @Column(name = "NEW_COUPON_NO")
    public String getNewCouponNo() {
        return newCouponNo;
    }

    public void setNewCouponNo(String newCouponNo) {
        this.newCouponNo = newCouponNo;
        if(newCouponNo == null){
              removeValidField("newCouponNo");
              return;
        }
        addValidField("newCouponNo");
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
    public Integer getRechargePlace() {
        return rechargePlace;
    }

    public void setRechargePlace(Integer rechargePlace) {
        this.rechargePlace = rechargePlace;
        if(rechargePlace == null){
              removeValidField("rechargePlace");
              return;
        }
        addValidField("rechargePlace");
    }
}