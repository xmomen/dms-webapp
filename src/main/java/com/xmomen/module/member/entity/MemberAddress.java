package com.xmomen.module.member.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_member_address")
public class MemberAddress extends BaseMybatisModel {
    /**
     * 
     */
    private String id;

    /**
     * 客户ID
     */
    private Integer cdMemberId;

    /**
     * 
     */
    private Integer province;

    /**
     * 城市
     */
    private Integer city;

    /**
     * 区域
     */
    private Integer area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 完整地址
     */
    private String fullAddress;

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "cd_member_id")
    public Integer getCdMemberId() {
        return cdMemberId;
    }

    public void setCdMemberId(Integer cdMemberId) {
        this.cdMemberId = cdMemberId;
        if(cdMemberId == null){
              removeValidField("cdMemberId");
              return;
        }
        addValidField("cdMemberId");
    }

    @Column(name = "province")
    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
        if(province == null){
              removeValidField("province");
              return;
        }
        addValidField("province");
    }

    @Column(name = "city")
    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
        if(city == null){
              removeValidField("city");
              return;
        }
        addValidField("city");
    }

    @Column(name = "area")
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
        if(area == null){
              removeValidField("area");
              return;
        }
        addValidField("area");
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        if(address == null){
              removeValidField("address");
              return;
        }
        addValidField("address");
    }

    @Column(name = "full_address")
    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        if(fullAddress == null){
              removeValidField("fullAddress");
              return;
        }
        addValidField("fullAddress");
    }
}