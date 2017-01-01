package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 2016/5/19.
 */
public @Data
class OrderQuery implements Serializable {

    private Integer id;
    private String orderNo;
    private String[] orderNos;
    private String keyword;
    private Integer orderStatus;
    private Integer packingTaskStatus;
    private Integer packingTaskUserId;
    private String packingTaskCreateTimeStart;
    private String packingTaskCreateTimeEnd;
    private Integer createUserId;
    private boolean isHasPackingTaskUserId;
    private Integer managerId;
    private Integer companyId;
    private String consigneeName;
    private boolean hasNoShowCancel;
    private Integer showDespatch;
    private Integer showPackingAssigns;
    
    private String orderCreateTimeStart;
    private String orderCreateTimeEnd;
    private String despatchExpressCode;
    
    private Integer despatchExpressId;
    
    private String couponNumber;
    
    private String appointmentTimeStart;
    private String appointmentTimeEnd;
    
    private Integer isTwoSend;
}
