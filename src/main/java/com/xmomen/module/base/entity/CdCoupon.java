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
@Table(name = "cd_coupon")
public class CdCoupon extends BaseMybatisModel {
    /**
     * 
     */
    private Integer cdCouponId;

    /**
     * 卡或者劵,1-卡，2-劵
     */
    private Integer couponType;

    /**
     * 卡号
     */
    private String couponNumber;

    /**
     * 密码
     */
    private String couponPassword;

    /**
     * 有效开始时间
     */
    private Date beginTime;

    /**
     * 有效结束时间
     */
    private Date endTime;

    /**
     * 固定金额
     */
    private BigDecimal price;

    /**
     * 固定次数
     */
    private Integer count;

    /**
     * 0-未使用，1-已使用
     */
    private Integer isUsed;

    /**
     * 0-无效，1-有效
     */
    private Integer isUseful;

    /**
     * 0-否，1-是
     */
    private Integer isFeedback;

    /**
     * 
     */
    private String notes;

    @Column(name = "CD_COUPON_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getCdCouponId() {
        return cdCouponId;
    }

    public void setCdCouponId(Integer cdCouponId) {
        this.cdCouponId = cdCouponId;
        if(cdCouponId == null){
              removeValidField("cdCouponId");
              return;
        }
        addValidField("cdCouponId");
    }

    @Column(name = "COUPON_TYPE")
    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
        if(couponType == null){
              removeValidField("couponType");
              return;
        }
        addValidField("couponType");
    }

    @Column(name = "COUPON_NUMBER")
    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
        if(couponNumber == null){
              removeValidField("couponNumber");
              return;
        }
        addValidField("couponNumber");
    }

    @Column(name = "COUPON_PASSWORD")
    public String getCouponPassword() {
        return couponPassword;
    }

    public void setCouponPassword(String couponPassword) {
        this.couponPassword = couponPassword;
        if(couponPassword == null){
              removeValidField("couponPassword");
              return;
        }
        addValidField("couponPassword");
    }

    @Column(name = "BEGIN_TIME")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        if(beginTime == null){
              removeValidField("beginTime");
              return;
        }
        addValidField("beginTime");
    }

    @Column(name = "END_TIME")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        if(endTime == null){
              removeValidField("endTime");
              return;
        }
        addValidField("endTime");
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

    @Column(name = "COUNT")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        if(count == null){
              removeValidField("count");
              return;
        }
        addValidField("count");
    }

    @Column(name = "IS_USED")
    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
        if(isUsed == null){
              removeValidField("isUsed");
              return;
        }
        addValidField("isUsed");
    }

    @Column(name = "IS_USEFUL")
    public Integer getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
        if(isUseful == null){
              removeValidField("isUseful");
              return;
        }
        addValidField("isUseful");
    }

    @Column(name = "IS_FEEDBACK")
    public Integer getIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(Integer isFeedback) {
        this.isFeedback = isFeedback;
        if(isFeedback == null){
              removeValidField("isFeedback");
              return;
        }
        addValidField("isFeedback");
    }

    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
        if(notes == null){
              removeValidField("notes");
              return;
        }
        addValidField("notes");
    }
}