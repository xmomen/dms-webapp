package com.xmomen.module.receipt.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_receiving_code_request")
public class TbReceivingCodeRequest extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 请求人
     */
    private String requestUser;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 请求人手机号
     */
    private String requestPhone;

    /**
     * 请求人所属快递商
     */
    private Integer requestExpressId;

    @Column(name = "id")
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

    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        if(orderNo == null){
              removeValidField("orderNo");
              return;
        }
        addValidField("orderNo");
    }

    @Column(name = "request_user")
    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
        if(requestUser == null){
              removeValidField("requestUser");
              return;
        }
        addValidField("requestUser");
    }

    @Column(name = "request_time")
    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
        if(requestTime == null){
              removeValidField("requestTime");
              return;
        }
        addValidField("requestTime");
    }

    @Column(name = "request_phone")
    public String getRequestPhone() {
        return requestPhone;
    }

    public void setRequestPhone(String requestPhone) {
        this.requestPhone = requestPhone;
        if(requestPhone == null){
              removeValidField("requestPhone");
              return;
        }
        addValidField("requestPhone");
    }

    @Column(name = "request_express_id")
    public Integer getRequestExpressId() {
        return requestExpressId;
    }

    public void setRequestExpressId(Integer requestExpressId) {
        this.requestExpressId = requestExpressId;
        if(requestExpressId == null){
              removeValidField("requestExpressId");
              return;
        }
        addValidField("requestExpressId");
    }
}