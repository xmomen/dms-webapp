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
@Table(name = "tb_pick")
public class TbPick extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 卡号
     */
    private String couponNo;

    /**
     * 采摘重量
     */
    private BigDecimal pickWeight;

    /**
     * 采摘付款方式（1-刷卡，2-现金+刷卡，3-现金）
     */
    private Integer pickPayType;

    /**
     * 采摘总金额(刷卡+现金)
     */
    private BigDecimal pickTotalPrice;

    /**
     * 刷卡金额
     */
    private BigDecimal pickCradPrice;

    /**
     * 现金
     */
    private BigDecimal pickCasePrice;

    /**
     * 采摘日期
     */
    private Date pickDate;

    /**
     * 采摘点
     */
    private Integer pickPlace;

    /**
     * 结算人
     */
    private Integer pickPlaceUser;

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

    @Column(name = "PICK_WEIGHT")
    public BigDecimal getPickWeight() {
        return pickWeight;
    }

    public void setPickWeight(BigDecimal pickWeight) {
        this.pickWeight = pickWeight;
        if(pickWeight == null){
              removeValidField("pickWeight");
              return;
        }
        addValidField("pickWeight");
    }

    @Column(name = "PICK_PAY_TYPE")
    public Integer getPickPayType() {
        return pickPayType;
    }

    public void setPickPayType(Integer pickPayType) {
        this.pickPayType = pickPayType;
        if(pickPayType == null){
              removeValidField("pickPayType");
              return;
        }
        addValidField("pickPayType");
    }

    @Column(name = "PICK_TOTAL_PRICE")
    public BigDecimal getPickTotalPrice() {
        return pickTotalPrice;
    }

    public void setPickTotalPrice(BigDecimal pickTotalPrice) {
        this.pickTotalPrice = pickTotalPrice;
        if(pickTotalPrice == null){
              removeValidField("pickTotalPrice");
              return;
        }
        addValidField("pickTotalPrice");
    }

    @Column(name = "PICK_CRAD_PRICE")
    public BigDecimal getPickCradPrice() {
        return pickCradPrice;
    }

    public void setPickCradPrice(BigDecimal pickCradPrice) {
        this.pickCradPrice = pickCradPrice;
        if(pickCradPrice == null){
              removeValidField("pickCradPrice");
              return;
        }
        addValidField("pickCradPrice");
    }

    @Column(name = "PICK_CASE_PRICE")
    public BigDecimal getPickCasePrice() {
        return pickCasePrice;
    }

    public void setPickCasePrice(BigDecimal pickCasePrice) {
        this.pickCasePrice = pickCasePrice;
        if(pickCasePrice == null){
              removeValidField("pickCasePrice");
              return;
        }
        addValidField("pickCasePrice");
    }

    @Column(name = "PICK_DATE")
    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
        if(pickDate == null){
              removeValidField("pickDate");
              return;
        }
        addValidField("pickDate");
    }

    @Column(name = "PICK_PLACE")
    public Integer getPickPlace() {
        return pickPlace;
    }

    public void setPickPlace(Integer pickPlace) {
        this.pickPlace = pickPlace;
        if(pickPlace == null){
              removeValidField("pickPlace");
              return;
        }
        addValidField("pickPlace");
    }

    @Column(name = "PICK_PLACE_USER")
    public Integer getPickPlaceUser() {
        return pickPlaceUser;
    }

    public void setPickPlaceUser(Integer pickPlaceUser) {
        this.pickPlaceUser = pickPlaceUser;
        if(pickPlaceUser == null){
              removeValidField("pickPlaceUser");
              return;
        }
        addValidField("pickPlaceUser");
    }
}