package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import com.xmomen.module.base.model.ItemModel;
import com.xmomen.module.base.model.ItemQuery;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.entity.*;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class OrderService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    ItemService itemService;

    /**
     * 查询订单
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<OrderModel> getOrderList(OrderQuery orderQuery, Integer limit, Integer offset){
        return (Page<OrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderList", orderQuery, limit, offset);
    }

    /**
     * 查询订单
     * @param orderQuery
     * @return
     */
    public List<OrderModel> getOrderList(OrderQuery orderQuery){
        return mybatisDao.getSqlSessionTemplate().selectList(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderList", orderQuery);
    }

    public OrderModel getOneOrder(OrderQuery orderQuery){
        if(orderQuery == null || (orderQuery.getId() == null && orderQuery.getOrderNo() == null)){
            return null;
        }
        List<OrderModel> orderModelList = getOrderList(orderQuery);
        if(orderModelList != null && !orderModelList.isEmpty() && orderModelList.size() == 1){
            return orderModelList.get(0);
        }
        return null;
    }

    /**
     * 创建订单
     * @param createOrder
     * @return
     */
    @Transactional
    public TbOrder createOrder(CreateOrder createOrder){
        String orderNo = DateUtils.getDateTimeString();
        List<Integer> itemIdList = new ArrayList<Integer>();
        for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
            itemIdList.add(orderItem.getOrderItemId());
        }
        ItemQuery itemQuery = new ItemQuery();
        Integer[] array = new Integer[itemIdList.size()];
        itemQuery.setIds(itemIdList.toArray(array));
        itemQuery.setCompanyId(createOrder.getCompanyId());
        List<ItemModel> itemList = itemService.queryItemList(itemQuery);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ItemModel cdItem : itemList) {
            for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
                if(cdItem.getId().equals(orderItem.getOrderItemId())){
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getPricingManner());
                    if(cdItem.getDiscountPrice() != null){
                        tbOrderItem.setItemPrice(cdItem.getDiscountPrice());
                    }else{
                        tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    }
                    totalAmount = totalAmount.add(tbOrderItem.getItemPrice().multiply(orderItem.getItemQty()));
                    mybatisDao.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus("1");// 订单新建
        tbOrder.setPayStatus(0);//待支付
        tbOrder.setTransportMode(1);// 默认快递
        tbOrder.setConsigneeName(createOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(createOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(createOrder.getConsigneePhone());
        tbOrder.setCreateTime(mybatisDao.getSysdate());
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setPaymentMode(createOrder.getPaymentMode());
        tbOrder.setMemberCode(createOrder.getMemberCode());
        tbOrder.setRemark(createOrder.getRemark());
        tbOrder.setOrderType(createOrder.getOrderType());
        tbOrder.setOrderNo(orderNo);
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setCreateUserId(createOrder.getCreateUserId());
        //生成收货码
		TbOrderRef orderRef = new TbOrderRef();
		orderRef.setOrderNo(orderNo);
		orderRef.setRefType("SHOU_HUO_NO");
		orderRef.setRefValue(String.valueOf((int)((Math.random()*9+1)*100000)));
		mybatisDao.insert(orderRef);
        totalAmount = totalAmount.subtract(createOrder.getDiscountPrice());
        //订单总金额 如果是劵的 则就是劵面金额 不用累计商品总金额
        if(tbOrder.getOrderType() == 2){
        	 tbOrder.setTotalAmount(createOrder.getTotalPrice());
        }else{
            tbOrder.setTotalAmount(totalAmount);
            tbOrder.setDiscountPrice(createOrder.getDiscountPrice());
        }
        tbOrder.setAppointmentTime(createOrder.getAppointmentTime());
        tbOrder = mybatisDao.insertByModel(tbOrder);
        if(StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null){
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setOrderNo(orderNo);
            tbOrderRelation.setRefType(OrderMapper.ORDER_PAY_RELATION_CODE);// 订单支付关系
            tbOrderRelation.setRefValue(createOrder.getPaymentRelationNo());
            mybatisDao.insert(tbOrderRelation);
        }
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(tbOrder.getOrderNo());
        payOrder.setAmount(totalAmount);
        payOrder(payOrder);
        return tbOrder;
    }

    /**
     * 更新订单
     * @param updateOrder
     * @return
     */
    @Transactional
    public TbOrder updateOrder(UpdateOrder updateOrder){
        String orderNo = updateOrder.getOrderNo();
        TbOrder oldTbOrder = new TbOrder();
        oldTbOrder.setOrderNo(updateOrder.getOrderNo());
        oldTbOrder = mybatisDao.selectOneByModel(oldTbOrder);
        List<String> itemCodeList = new ArrayList<String>();
        for (UpdateOrder.OrderItem orderItem : updateOrder.getOrderItemList()) {
            itemCodeList.add(orderItem.getItemCode());
        }
        ItemQuery itemQuery = new ItemQuery();
        String[] array = new String[itemCodeList.size()];
        itemQuery.setItemCodes(itemCodeList.toArray(array));
        itemQuery.setCompanyId(updateOrder.getCompanyId());
        List<ItemModel> itemList = itemService.queryItemList(itemQuery);
        BigDecimal totalAmount = BigDecimal.ZERO;
        TbOrderItemExample tbOrderItemExample = new TbOrderItemExample();
        tbOrderItemExample.createCriteria().andOrderNoEqualTo(orderNo);
        mybatisDao.deleteByExample(tbOrderItemExample);
        for (ItemModel cdItem : itemList) {
            for (UpdateOrder.OrderItem orderItem : updateOrder.getOrderItemList()) {
                if(cdItem.getId().equals(orderItem.getOrderItemId())){
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getSpec());
                    if(cdItem.getDiscountPrice() != null){
                        tbOrderItem.setItemPrice(cdItem.getDiscountPrice());
                    }else{
                        tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    }
                    totalAmount = totalAmount.add(tbOrderItem.getItemPrice().multiply(orderItem.getItemQty()));
                    mybatisDao.save(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setId(updateOrder.getId());
        tbOrder.setTransportMode(1);// 默认快递
        tbOrder.setConsigneeName(updateOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(updateOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(updateOrder.getConsigneePhone());
        tbOrder.setOrderSource(updateOrder.getOrderSource());
        tbOrder.setPaymentMode(updateOrder.getPaymentMode());
        tbOrder.setMemberCode(updateOrder.getMemberCode());
        tbOrder.setRemark(updateOrder.getRemark());
        tbOrder.setOrderType(updateOrder.getOrderType());
        tbOrder.setOrderSource(updateOrder.getOrderSource());
        totalAmount = totalAmount.subtract(updateOrder.getDiscountPrice());
        //订单总金额 如果是劵的 则就是劵面金额 不用累计商品总金额
        if(tbOrder.getOrderType() == 2){
        	 tbOrder.setTotalAmount(updateOrder.getTotalPrice());
        }else{
            tbOrder.setTotalAmount(totalAmount);
            tbOrder.setDiscountPrice(updateOrder.getDiscountPrice());
        }
        tbOrder.setAppointmentTime(updateOrder.getAppointmentTime());
        mybatisDao.update(tbOrder);
        if(StringUtils.trimToNull(updateOrder.getPaymentRelationNo()) != null){
            TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
            tbOrderRelationExample.createCriteria()
                    .andOrderNoEqualTo(orderNo)
                    .andRefTypeEqualTo(OrderMapper.ORDER_PAY_RELATION_CODE);
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setRefValue(updateOrder.getPaymentRelationNo());
            mybatisDao.updateOneByExampleSelective(tbOrderRelation, tbOrderRelationExample);
        }
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setOrderNo(oldTbOrder.getOrderNo());
        refundOrder.setAmount(oldTbOrder.getTotalAmount());
        refundOrder.setRemark("订单更新，金额变更");
        refundOrder(refundOrder);
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(oldTbOrder.getOrderNo());
        payOrder.setAmount(BigDecimal.valueOf(-1).multiply(oldTbOrder.getTotalAmount()));
        payOrder(payOrder);
        return tbOrder;
    }

    /**
     * 删除订单
     * @param id
     */
    public void deleteOrder(Integer id){
        mybatisDao.deleteByPrimaryKey(TbOrder.class, id);
    }

    /**
     * 取消订单
     * @param id
     */
    public void cancelOrder(Integer id){
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andIdEqualTo(id);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus("9");
        mybatisDao.updateOneByExampleSelective(tbOrder, tbOrderExample);
    }

    public void updateOrderStatus(String orderNo, String orderStatus){
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus(orderStatus);
        mybatisDao.updateOneByExampleSelective(tbOrder, tbOrderExample);
    }

    public void updateOrderStatus(Integer id, String orderStatus){
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andIdEqualTo(id);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus(orderStatus);
        mybatisDao.updateOneByExampleSelective(tbOrder, tbOrderExample);
    }

    /**
     * 订单退款／冲销
     * @param refundOrder
     */
    public void refundOrder(RefundOrder refundOrder){
        TbTradeRecord tbTradeRecord = new TbTradeRecord();
        tbTradeRecord.setAmount(refundOrder.getAmount());
        tbTradeRecord.setTradeNo(refundOrder.getOrderNo());
        tbTradeRecord.setTradeType("REFUND");
        tbTradeRecord.setCreateTime(mybatisDao.getSysdate());
        mybatisDao.insert(tbTradeRecord);
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andOrderNoEqualTo(refundOrder.getOrderNo());
        TbOrder tbOrder1 = new TbOrder();
        tbOrder1.setPayStatus(0);//待支付
        mybatisDao.updateOneByExampleSelective(tbOrder1, tbOrderExample);
    }

    /**
     * 支付订单
     * @param payOrder
     */
    public void payOrder(PayOrder payOrder) throws IllegalArgumentException {
        TbOrderRelation tbOrderRelation = new TbOrderRelation();
        tbOrderRelation.setRefType(OrderMapper.ORDER_PAY_RELATION_CODE);
        tbOrderRelation.setOrderNo(payOrder.getOrderNo());
        tbOrderRelation = mybatisDao.selectOneByModel(tbOrderRelation);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderNo(payOrder.getOrderNo());
        tbOrder = mybatisDao.selectOneByModel(tbOrder);
        if(tbOrder.getPaymentMode().equals(5) || tbOrder.getPaymentMode().equals(7)){
            CdCoupon cdCoupon = new CdCoupon();
            cdCoupon.setCouponNumber(tbOrderRelation.getRefValue());
            cdCoupon = mybatisDao.selectOneByModel(cdCoupon);
            if(tbOrder.getOrderType() == 1){
                // 卡内支付
                if(cdCoupon != null &&
                        cdCoupon.getUserPrice() != null &&
                        tbOrder.getTotalAmount() != null &&
                        tbOrder.getOrderType() == 1 &&
                        cdCoupon.getUserPrice().compareTo(tbOrder.getTotalAmount()) < 0){
                    throw new IllegalArgumentException("卡内余额不足，请充值");
                }
                BigDecimal amount = cdCoupon.getUserPrice().subtract(tbOrder.getTotalAmount());
                CdCouponExample cdCouponExample = new CdCouponExample();
                cdCouponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
                CdCoupon updateCdCoupon = new CdCoupon();
                updateCdCoupon.setUserPrice(amount);
                mybatisDao.updateOneByExampleSelective(updateCdCoupon, cdCouponExample);
                TbTradeRecord tbTradeRecord = new TbTradeRecord();
                tbTradeRecord.setAmount(payOrder.getAmount());
                tbTradeRecord.setCreateTime(mybatisDao.getSysdate());
                tbTradeRecord.setTradeNo(payOrder.getOrderNo());
                tbTradeRecord.setTradeType("CARD");
                mybatisDao.insert(tbTradeRecord);
            }else if(tbOrder.getOrderType() == 2){
                Date now = mybatisDao.getSysdate();
                if(now.before(cdCoupon.getBeginTime())){
                    throw new IllegalArgumentException("未到此优惠券的使用日期，请在优惠券使用期限内使用此优惠券");
                }
                if(now.after(cdCoupon.getEndTime())){
                    throw new IllegalArgumentException("此优惠券已过期");
                }
                if(cdCoupon.getIsUseful() == 0){
                    throw new IllegalArgumentException("无效的优惠券");
                }
                if(cdCoupon.getIsUsed() == 1){
                    throw new IllegalArgumentException("此优惠券已被使用，请选择其它优惠券");
                }
                CdCouponExample cdCouponExample = new CdCouponExample();
                cdCouponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
                CdCoupon updateCdCoupon = new CdCoupon();
                updateCdCoupon.setIsUsed(1);
                mybatisDao.updateOneByExampleSelective(updateCdCoupon, cdCouponExample);
                TbTradeRecord tbTradeRecord = new TbTradeRecord();
                tbTradeRecord.setAmount(payOrder.getAmount());
                tbTradeRecord.setCreateTime(mybatisDao.getSysdate());
                tbTradeRecord.setTradeNo(payOrder.getOrderNo());
                tbTradeRecord.setTradeType("COUPON");
                mybatisDao.insert(tbTradeRecord);
            }
            TbOrderExample tbOrderExample = new TbOrderExample();
            tbOrderExample.createCriteria().andOrderNoEqualTo(payOrder.getOrderNo());
            TbOrder tbOrder1 = new TbOrder();
            tbOrder1.setPayStatus(1);//已支付
            mybatisDao.updateOneByExampleSelective(tbOrder1, tbOrderExample);
        }
    }

}
