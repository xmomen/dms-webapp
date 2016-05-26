package com.xmomen.module.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

@Entity
@Table(name = "cd_activity_address")
public class CdActivityAddress extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 卡号
     */
    private String couponNumber;

    /**
     * 
     */
    private String consignmentName;

    /**
     * 收货手机号
     */
    private String consignmentPhone;

    /**
     * 收货地址
     */
    private String consignmentAddress;
    
    /**
     * 赠送时间
     */
    private Date sendTime;

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

    @Column(name = "CONSIGNMENT_NAME")
    public String getConsignmentName() {
        return consignmentName;
    }

    public void setConsignmentName(String consignmentName) {
        this.consignmentName = consignmentName;
        if(consignmentName == null){
              removeValidField("consignmentName");
              return;
        }
        addValidField("consignmentName");
    }

    @Column(name = "CONSIGNMENT_PHONE")
    public String getConsignmentPhone() {
        return consignmentPhone;
    }

    public void setConsignmentPhone(String consignmentPhone) {
        this.consignmentPhone = consignmentPhone;
        if(consignmentPhone == null){
              removeValidField("consignmentPhone");
              return;
        }
        addValidField("consignmentPhone");
    }

    @Column(name = "CONSIGNMENT_ADDRESS")
    public String getConsignmentAddress() {
        return consignmentAddress;
    }

    public void setConsignmentAddress(String consignmentAddress) {
        this.consignmentAddress = consignmentAddress;
        if(consignmentAddress == null){
              removeValidField("consignmentAddress");
              return;
        }
        addValidField("consignmentAddress");
    }
    @Column(name = "SEND_TIME")
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        if(sendTime == null){
              removeValidField("sendTime");
              return;
        }
        addValidField("sendTime");
    }
}