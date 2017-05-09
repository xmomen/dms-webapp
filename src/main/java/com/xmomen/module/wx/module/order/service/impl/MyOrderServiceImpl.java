package com.xmomen.module.wx.module.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.resource.service.ResourceUtilsService;
import com.xmomen.module.wx.model.PayAttachModel;
import com.xmomen.module.wx.module.order.mapper.MyOrderMapper;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderModel;
import com.xmomen.module.wx.module.order.model.OrderProductItem;
import com.xmomen.module.wx.module.order.model.OrderStatisticModel;
import com.xmomen.module.wx.module.order.service.MyOrderService;
import com.xmomen.module.wx.pay.entity.TbPayRecord;
import com.xmomen.module.wx.pay.model.PayResData;
import com.xmomen.module.wx.pay.model.RefundResData;
import com.xmomen.module.wx.pay.model.WeixinPayRecord;
import com.xmomen.module.wx.pay.service.PayRecordService;
import com.xmomen.module.wx.service.WeixinApiService;

@Service
public class MyOrderServiceImpl implements MyOrderService {

	static Logger log = LoggerFactory.getLogger(MyOrderServiceImpl.class);

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    OrderService orderService;
    
    @Autowired
    CouponService couponService;
    
    @Autowired
    PayRecordService payRecordService;
    
    @Autowired
    WeixinApiService weixinApiService;

    @Override
    public List<OrderModel> myOrder(MyOrderQuery myOrderQuery) {
        List<OrderModel> orders = mybatisDao.getSqlSessionTemplate().selectList(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "selectOrders", myOrderQuery);
        if (orders != null) {
            for (OrderModel order : orders) {
                List<OrderProductItem> items = order.getProducts();
                if (items != null) {
                    for (OrderProductItem item : items) {
                    	if (StringUtils.isEmpty(item.getPicUrl())) {
                    		item.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                        }
                        else {
                        	item.setPicUrl(ResourceUtilsService.getWholeHttpPath(item.getPicUrl()));
                        }
                    }
                }
                String orderStatus = order.getOrderStatus();
                if(orderCanCancel(orderStatus)) {
                	order.setCanCancel(true);
                } else {
                	order.setCanCancel(false);
                }
            }
        }
        return orders;
    }

    @Override
    public OrderDetailModel getOrderDetail(MyOrderQuery myOrderQuery) {
        if (myOrderQuery.getOrderId() == null && StringUtils.isEmpty(myOrderQuery.getOrderNo())) {
            return null;
        }
        OrderDetailModel orderDetail = mybatisDao.getSqlSessionTemplate().selectOne(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "getOrderDetail", myOrderQuery);
        if (orderDetail != null) {
            List<OrderProductItem> items = orderDetail.getProducts();
            for (OrderProductItem item : items) {
            	if (StringUtils.isEmpty(item.getPicUrl())) {
            		item.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                }
                else {
                	item.setPicUrl(ResourceUtilsService.getWholeHttpPath(item.getPicUrl()));
                }
            }
            String orderStatus = orderDetail.getOrderStatus();
            if(orderCanCancel(orderStatus)) {
            	orderDetail.setCanCancel(true);
            } else {
            	orderDetail.setCanCancel(false);
            }
        }
        return orderDetail;
    }

    @Override
    public Boolean confirmReceiveOrder(Integer orderId, Integer userId) throws Exception {
        TbOrder tbOrder = mybatisDao.selectByPrimaryKey(TbOrder.class, orderId);
        if (tbOrder == null || userId == null || !String.valueOf(userId).equals(tbOrder.getMemberCode())) {
            throw new BusinessException("订单不存在或者不属于当前用户!");
        }
        tbOrder.setOrderStatus("6");//确认本人收货
        tbOrder.setShouHuoDate(new Date());
        mybatisDao.update(tbOrder);
        return Boolean.TRUE;
    }

    /**
     * 未配送的订单才可以取消，如下
     * 0-等待付款 1-待采购
     */
    @Override
    @Transactional
    public Boolean cancelOrder(Integer orderId, Integer userId) throws Exception {
        TbOrder tbOrder = mybatisDao.selectByPrimaryKey(TbOrder.class, orderId);
        if (tbOrder == null || userId == null || !String.valueOf(userId).equals(tbOrder.getMemberCode())) {
            throw new BusinessException("订单不存在或者不属于当前用户!");
        }
        if(tbOrder.getOrderStatus() != null && tbOrder.getOrderStatus().equals(9)) {
        	throw new BusinessException("已取消的订单不能重复取消!");
        }
        /*Integer payStatus = tbOrder.getPayStatus();
        if (payStatus == 1) throw new IllegalArgumentException("订单已支付,不能取消!");*/
        String orderStatus = tbOrder.getOrderStatus();
        List<String> allowCancelStatus = new ArrayList<String>();
        allowCancelStatus.add("0");
        allowCancelStatus.add("1");
        if(allowCancelStatus.contains(orderStatus)) {
        	if(tbOrder.getOrderType().equals(1) || tbOrder.getOrderType().equals(2)) {
        		//卡类和券类已经支付过了，卡类支付直接把钱退到卡里，券类订单则让券继续可用
        		orderService.cancelOrder(orderId);
        	} else if(tbOrder.getOrderType().equals(0)) {
        		//如果为常规订单
        		Integer payStatus = tbOrder.getPayStatus();
        		if(payStatus != null && payStatus.equals(1)) {
        			Integer paymentMode = tbOrder.getPaymentMode();
        			if(paymentMode != null && paymentMode.equals(4)) {
        				////货到付款，即物流公司代收的付款方式,将支付状态回退为0
        				tbOrder.setPayStatus(0);
        			} else if (paymentMode != null && paymentMode.equals(8)) {
        				//微信类支付
        				String tradeNo = tbOrder.getOrderNo();
        				RefundResData refundResData = weixinApiService.refund(tradeNo, tbOrder.getTotalAmount().multiply(new BigDecimal(100)).intValue());
        				if(refundResData == null) {
        					throw new BusinessException("微信退款失败");
        				}
        			}
            		//其他第三方的支付，标注为取消状态，由batch统一去处理
        		}
                tbOrder.setOrderStatus("9");//取消订单
                mybatisDao.update(tbOrder);
        	}
        } else {
        	throw new BusinessException("已经处理的订单不能取消!");
        }
        return Boolean.TRUE;
    }

	@Override
	public Map<String, Integer> getOrderStatistic(Integer userId) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<OrderStatisticModel> orderStatisticModels = mybatisDao.getSqlSessionTemplate().selectList(MyOrderMapper.MY_ORDER_MAPPER_NAMESPACE + "getOrderStatistic", userId);
		int notPayCount = 0;
		if(!CollectionUtils.isEmpty(orderStatisticModels)) {
			for(OrderStatisticModel orderStatisticModel: orderStatisticModels) {
				if(orderStatisticModel.getPayStatus() != 1) {
					notPayCount += orderStatisticModel.getCount();
				}
				String statusDesc = orderStatisticModel.getOrderStatusDesc();
				if(statusDesc != null && !orderStatisticModel.getOrderStatus().equals(0)) {
					if(result.containsKey(statusDesc)) {
						result.put(statusDesc, result.get(statusDesc) + orderStatisticModel.getCount());
					} else {
						result.put(statusDesc, orderStatisticModel.getCount());
					}
				}
			}
		}
		if(notPayCount > 0) {
			result.put("待付款", notPayCount);
		}
		return result;
	}

	@Override
	@Transactional
	public void payCallBack(PayResData payResData) throws Exception {
		String attachement = payResData.getAttach();
		PayAttachModel payAttachModel = JSON.parseObject(attachement, PayAttachModel.class);
		String tradeId = payAttachModel.getTradeId();
		String tradeNo = payAttachModel.getTradeNo();
		int memberId = payAttachModel.getMemberId();
		TbPayRecord payRecordQuery = new TbPayRecord();
		payRecordQuery.setTradeNo(tradeNo);
		List<TbPayRecord> tbPayRecords = payRecordService.getTbpayRecordListByRecord(payRecordQuery);
		if(!CollectionUtils.isEmpty(tbPayRecords)) {
			for(TbPayRecord tbPayRecord: tbPayRecords) {
				String thePayRecordId = tbPayRecord.getId();
				if(tradeId.equals(thePayRecordId)) {
					//处理微信可能存在的重复通知
					return;
				}
				if(tbPayRecord.getTradeType()!=null && tbPayRecord.getTradeType().equals(1)
						&& tbPayRecord.getCompleteTime() != null) {
					//同一个微信订单已支付（出现的请况很少）
					log.error("出现订单重复支付的记录：tradeNo=" + tradeNo + ", tradeId=" + tradeId 
							+ ", transactionId=" + payResData.getTransaction_id() + ", openId=" + payAttachModel.getOpenId());
					return;
				}
			}
		}
		
		double totalFee = payResData.getTotal_fee();
		if(1 == payAttachModel.getType()) {
			//微信支付
			String orderNo = payAttachModel.getTradeNo();
			TbOrder query = new TbOrder();
			query.setOrderNo(orderNo);
			TbOrder tbOrder = mybatisDao.selectOneByModel(query);
			if(tbOrder == null) {
				log.error("订单不存在! --" + orderNo);
				throw new BusinessException("订单不存在!");
			}
			//设置为微信支付类型
			tbOrder.setPaymentMode(8);
			tbOrder.setPayStatus(1);
			tbOrder.setOrderStatus("1");
			mybatisDao.update(tbOrder);
		} else if(2 == payAttachModel.getType()) {
			//卡充值
			String couponNo = payAttachModel.getTradeNo();
			couponService.cardWxRecharge(couponNo, new BigDecimal(totalFee/100), memberId);
		} else {
			log.error("支付类型只能为1或2 -- type是" + payAttachModel.getType());
			throw new IllegalArgumentException("支付类型只能为1或2");
		}
		//插入支付记录到tb_pay_record表
		TbPayRecord tbPayRecord = new TbPayRecord();
		tbPayRecord.setId(tradeId);
		tbPayRecord.setTradeNo(payAttachModel.getTradeNo());
		tbPayRecord.setTradeType(payAttachModel.getType());
		tbPayRecord.setTotalFee(new BigDecimal(totalFee/100));
		tbPayRecord.setTransactionId(payResData.getTransaction_id());
		tbPayRecord.setTransactionTime(new Date());
		tbPayRecord.setCompleteTime(new Date());
		tbPayRecord.setOpenId(payAttachModel.getOpenId());
		
		payRecordService.insert(tbPayRecord);
	}

	private boolean orderCanCancel(String orderStatus) {
		if("0".equals(orderStatus) || "1".equals(orderStatus)) {
			return true;
		}
		return false;
	}
}
