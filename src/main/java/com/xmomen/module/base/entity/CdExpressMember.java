package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_express_member")
public class CdExpressMember extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 快递商id
     */
    private Integer cdExpressId;

    /**
     * 快递员姓名
     */
    private String memberName;

    /**
     * 电话号码
     */
    private String phone;

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

    @Column(name = "CD_EXPRESS_ID")
    public Integer getCdExpressId() {
        return cdExpressId;
    }

    public void setCdExpressId(Integer cdExpressId) {
        this.cdExpressId = cdExpressId;
        if(cdExpressId == null){
              removeValidField("cdExpressId");
              return;
        }
        addValidField("cdExpressId");
    }

    @Column(name = "MEMBER_NAME")
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
        if(memberName == null){
              removeValidField("memberName");
              return;
        }
        addValidField("memberName");
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        if(phone == null){
              removeValidField("phone");
              return;
        }
        addValidField("phone");
    }
}