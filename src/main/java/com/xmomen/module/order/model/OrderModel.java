package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Created by Jeng on 16/4/6.
 */
public
@Data
class OrderModel implements Serializable {

    /**
     * 订单ID
     */
    private Integer id;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 订单类型描述
     */
    private String orderTypeDesc;

    /**
     * 订单来源：1-微信订单，2-商城订单，3-客服下单，4-采摘订单
     */
    private Integer orderSource;

    /**
     * 订单来源描述
     */
    private String orderSourceDesc;

    /**
     * 付款方式
     */
    private Integer paymentMode;

    /**
     * 付款方式描述
     */
    private String paymentModeDesc;

    /**
     * 订单编码
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 装箱任务负责人名称
     */
    private String packingTaskUsername;
    /**
     * 装箱任务负责人ID
     */
    private Integer packingTaskUserId;

    /**
     * 订单装箱任务状态
     */
    private Integer packingTaskStatus;
    /**
     * 装箱任务状态描述
     */
    private String packingTaskStatusDesc;

    /**
     * 装箱任务分配时间
     */
    private Date packingTaskCreateTime;

    /**
     * 装箱任务开始时间
     */
    private Date packingTaskStartTime;

    /**
     * 装箱任务完成时间
     */
    private Date packingTaskEndTime;

    /**
     * 订单状态描述
     */
    private String orderStatusDesc;

    /**
     * 收货人手机
     */
    private String consigneePhone;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人地址
     */
    private String consigneeAddress;

    /**
     * 运送方式 1-快递，2-自提
     */
    private Integer transportMode;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 折扣价
     */
    private BigDecimal discountPrice;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单创建人ID
     */
    private Integer createUserId;

    private String createUser;

    /**
     * 配送时间
     */
    private Date appointmentTime;

    /**
     * 支付卡（券）号
     */
    private String couponNumber;

    /**
     * 快递商
     */
    private String expressName;

    private Integer despatchExpressId;

    /**
     * 项目经理
     */
    private String managerName;
    /**
     * 单位
     */
    private String companyName;

    /**
     * 总箱数
     */
    private List<PackingModel> packingModels;

    private String batchNo;

    //每批次的订单数
    private int batchNum;

    private int xiajia;
    /**
     * 付款方式
     */
    private Integer otherPaymentMode;
    
    private String otherPaymentModeDesc;
    
    private BigDecimal otherPayAmount;
    
    private String receivingCode;

}
