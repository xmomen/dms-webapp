package com.xmomen.module.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmomen.module.stock.service.StockService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CouponModel;
import com.xmomen.module.base.model.CouponRelationItem;
import com.xmomen.module.base.model.ItemModel;
import com.xmomen.module.base.model.ItemQuery;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.service.MemberAddressService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderExample;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.entity.TbOrderItemExample;
import com.xmomen.module.order.entity.TbOrderRef;
import com.xmomen.module.order.entity.TbOrderRefExample;
import com.xmomen.module.order.entity.TbOrderRelation;
import com.xmomen.module.order.entity.TbOrderRelationExample;
import com.xmomen.module.order.entity.TbPurchase;
import com.xmomen.module.order.entity.TbTradeRecord;
import com.xmomen.module.order.entity.TbTradeRecordExample;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.model.PayOrder;
import com.xmomen.module.order.model.RefundOrder;
import com.xmomen.module.order.model.UpdateOrder;
import com.xmomen.module.order.model.WxCreateOrder;
import com.xmomen.module.plan.entity.TbTablePlan;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.service.ProductService;
import com.xmomen.module.report.model.OrderReport;
import com.xmomen.module.wx.module.cart.service.CartService;
import com.xmomen.module.wx.module.order.model.MyOrderQuery;
import com.xmomen.module.wx.module.order.model.OrderDetailModel;
import com.xmomen.module.wx.module.order.model.OrderProductItem;
import com.xmomen.module.wx.module.order.model.PayOrderModel;
import com.xmomen.module.wx.module.order.service.MyOrderService;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class OrderService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    ItemService itemService;

    @Autowired
    CouponService couponService;

    @Autowired
    MyOrderService myOrderService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartServcie;

    @Autowired
    MemberAddressService memberAddressService;

    @Autowired
    StockService stockService;

    /**
     * 查询订单
     *
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<OrderModel> getOrderList(OrderQuery orderQuery, Integer limit, Integer offset) {
        return (Page<OrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderList", orderQuery, limit, offset);
    }

    /**
     * 查询装箱任务
     *
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<OrderModel> getPackageTaskList(OrderQuery orderQuery, Integer limit, Integer offset) {
        return (Page<OrderModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getPackageTaskList", orderQuery, limit, offset);
    }

    public int getCountBatch(String btachNo) {
        return mybatisDao.getSqlSessionTemplate().selectOne(OrderMapper.ORDER_MAPPER_NAMESPACE + "countBatchOrder", btachNo);
    }

    /**
     * 查询订单
     *
     * @param orderQuery
     * @return
     */
    public List<OrderModel> getOrderList(OrderQuery orderQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderList", orderQuery);
    }

    public List<OrderReport> getOrderReportList(OrderQuery orderQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(OrderMapper.ORDER_MAPPER_NAMESPACE + "getOrderReportList", orderQuery);
    }

    public OrderModel getOneOrder(OrderQuery orderQuery) {
        if (orderQuery == null || (orderQuery.getId() == null && orderQuery.getOrderNo() == null)) {
            return null;
        }
        List<OrderModel> orderModelList = getOrderList(orderQuery);
        if (orderModelList != null && !orderModelList.isEmpty() && orderModelList.size() == 1) {
            return orderModelList.get(0);
        }
        return null;
    }

    /**
     * 创建订单
     *
     * @param createOrder
     * @return
     */
    @Transactional
    public TbOrder createOrder(CreateOrder createOrder) throws BusinessException {
        String orderNo = createOrder.getOrderNo();
        if (StringUtils.isEmpty(orderNo)) {
            orderNo = DateUtils.getDateTimeString();
        }
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
        int xiajia = 0;
        for (ItemModel cdItem : itemList) {
            for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
                //查看商品是否下架了
                if (cdItem.getSellStatus() == 0) {
                    xiajia = 1;
                }
                if (cdItem.getId().equals(orderItem.getOrderItemId())) {
                    //判断库存是否有
                    boolean flag = stockService.checkStock(orderItem.getOrderItemId(), orderItem.getItemQty().intValue());
                    //库存不足
                    if (!flag) {
                        throw new BusinessException("商品：" + cdItem.getItemName() + "库存不足。下单失败");
                    }
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getPricingManner());
                    if (cdItem.getDiscountPrice() != null) {
                        tbOrderItem.setItemPrice(cdItem.getDiscountPrice());
                    }
                    else {
                        tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    }
                    totalAmount = totalAmount.add(tbOrderItem.getItemPrice().multiply(orderItem.getItemQty()));
                    tbOrderItem.setCreateDate(mybatisDao.getSysdate());
                    tbOrderItem.setCreateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                    tbOrderItem.setUpdateDate(mybatisDao.getSysdate());
                    tbOrderItem.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                    mybatisDao.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        // 订单新建 待采购状态
        tbOrder.setOrderStatus("1");
        tbOrder.setPayStatus(0);//待支付
        tbOrder.setTransportMode(1);// 默认快递
        tbOrder.setConsigneeName(createOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(createOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(createOrder.getConsigneePhone());
        tbOrder.setCreateTime(mybatisDao.getSysdate());
        tbOrder.setPaymentMode(createOrder.getPaymentMode());
        tbOrder.setOtherPaymentMode(createOrder.getOtherPaymentMode());
        tbOrder.setMemberCode(createOrder.getMemberCode());
        tbOrder.setRemark(createOrder.getRemark());
        tbOrder.setOrderType(createOrder.getOrderType());
        tbOrder.setOrderNo(orderNo);
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setCreateUserId(createOrder.getCreateUserId());
        tbOrder.setUpdateDate(mybatisDao.getSysdate());
        tbOrder.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        tbOrder.setManagerId(createOrder.getManagerId());
        tbOrder.setCompanyId(createOrder.getCompanyId());
        tbOrder.setBatchNo(createOrder.getBatchNo());
        tbOrder.setXiajia(xiajia);
        //生成收货码
        TbOrderRef orderRef = new TbOrderRef();
        orderRef.setOrderNo(orderNo);
        orderRef.setRefType("SHOU_HUO_NO");
        orderRef.setRefValue(String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
        mybatisDao.insert(orderRef);
        totalAmount = totalAmount.subtract(createOrder.getDiscountPrice() == null ? BigDecimal.ZERO : createOrder.getDiscountPrice());
        //订单总金额 如果是劵的 或者是餐桌计划的 则就是劵面金额 不用累计商品总金额
        if (tbOrder.getOrderType() == 2 || tbOrder.getOrderType() == 3) {
            tbOrder.setTotalAmount(createOrder.getTotalPrice());
            totalAmount = createOrder.getTotalPrice();
            tbOrder.setDiscountPrice(BigDecimal.ZERO);
        }
        else {
            tbOrder.setTotalAmount(totalAmount);
            tbOrder.setDiscountPrice(createOrder.getDiscountPrice());
        }
        tbOrder.setAppointmentTime(createOrder.getAppointmentTime());
        tbOrder = mybatisDao.insertByModel(tbOrder);

        //扣除订单相应的产品库存
        for (CreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
            //扣除库存
            stockService.changeStockNum(orderItem.getOrderItemId(), 0 - orderItem.getItemQty().intValue(), tbOrder.getId(), "商品下单商品：" + orderItem.getItemQty() + "扣除,库存减少");
        }

        if (StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null && createOrder.getOrderType() > 0) {
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

    @Transactional
    public void batchCreateOrder(CreateOrder createOrder) throws BusinessException {
        if (createOrder.getBatchNumber() == null) {
            createOrder(createOrder);
        }
        else {
            //生成一个随机批次号
            createOrder.setBatchNo(String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
            for (int i = 0; i < createOrder.getBatchNumber(); i++) {
                createOrder(createOrder);
            }
        }
    }

    /**
     * 更新订单
     *
     * @param updateOrder
     * @return
     */
    @Transactional
    public TbOrder updateOrder(UpdateOrder updateOrder) throws BusinessException {
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
        //查询订单的原商品信息
        TbOrderItemExample tbOrderItemExample = new TbOrderItemExample();
        tbOrderItemExample.createCriteria().andOrderNoEqualTo(orderNo);
        List<TbOrderItem> tbOrderItems = mybatisDao.selectByExample(tbOrderItemExample);
//        mybatisDao.deleteByExample(tbOrderItemExample);
        Map<String, TbOrderItem> oldOrderItemMap = new HashMap<String, TbOrderItem>();
        for (TbOrderItem tbOrderItem : tbOrderItems) {
            oldOrderItemMap.put(tbOrderItem.getItemCode(), tbOrderItem);
        }
        boolean flagExists = false;
        //是否有下架商品 0 无 1有
        int xiajia = 0;
        List<UpdateOrder.OrderItem> changeOrderItems = new ArrayList<>();
        for (ItemModel cdItem : itemList) {
            for (UpdateOrder.OrderItem orderItem : updateOrder.getOrderItemList()) {
                if (cdItem.getId().equals(orderItem.getOrderItemId())) {
                    //查看商品是否下架了
                    if (cdItem.getSellStatus() == 0) {
                        xiajia = 1;
                    }
                    //如果订单状态大于等于3则认为是补货或者更换货的 需要生成采购计划
                    if (Integer.parseInt(oldTbOrder.getOrderStatus()) >= 3) {
                        //计算是否是新增的商品
                        for (TbOrderItem orderItemDb : tbOrderItems) {
                            if (orderItemDb.getItemId().equals(orderItem.getOrderItemId())) {
                                flagExists = true;
                            }
                        }
                        if (!flagExists) {
                            changeOrderItems.add(orderItem);
                        }
                        flagExists = false;
                    }
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    //map存在 说明是更新
                    if (oldOrderItemMap.containsKey(cdItem.getItemCode())) {
                        TbOrderItem oldOrderItem = oldOrderItemMap.get(cdItem.getItemCode());
                        tbOrderItem.setId(oldOrderItem.getId());
                        //map 移除
                        oldOrderItemMap.remove(cdItem.getItemCode());

                        //查看是增加还是减少
                        Integer oldQty = oldOrderItem.getItemQty().intValue();
                        Integer newQty = orderItem.getItemQty().intValue();
                        if (newQty != oldQty) {
                            //增加
                            if (newQty > oldQty) {
                                //校验库存是否够
                                stockService.checkStock(oldOrderItem.getItemId(), newQty - oldQty);
                                //将库存改变
                                stockService.changeStockNum(oldOrderItem.getItemId(), oldQty - newQty, updateOrder.getId(), "订单商品" + oldQty + "变更为" + newQty + "，库存减少");
                            }
                            else {
                                //将库存改变
                                stockService.changeStockNum(oldOrderItem.getItemId(), oldQty - newQty, updateOrder.getId(), "订单商品" + oldQty + "变更为" + newQty + "，库存增加");
                            }
                        }
                    }
                    //新增加的商品
                    else {
                        tbOrderItem.setCreateDate(mybatisDao.getSysdate());
                        tbOrderItem.setCreateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                        //校验库存是否够
                        stockService.checkStock(cdItem.getId(), orderItem.getItemQty().intValue());
                        //将库存改变
                        stockService.changeStockNum(cdItem.getId(), 0 - orderItem.getItemQty().intValue(), updateOrder.getId(), "订单商品下单：" + orderItem.getItemQty().intValue() + "，库存减少");
                    }
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getSpec());
                    if (cdItem.getDiscountPrice() != null) {
                        tbOrderItem.setItemPrice(cdItem.getDiscountPrice());
                    }
                    else {
                        tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    }
                    totalAmount = totalAmount.add(tbOrderItem.getItemPrice().multiply(orderItem.getItemQty()));
                    tbOrderItem.setUpdateDate(mybatisDao.getSysdate());
                    tbOrderItem.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                    mybatisDao.save(tbOrderItem);
                }
            }
        }

        //将map里面还有的进行删除
        for (String key : oldOrderItemMap.keySet()) {
            mybatisDao.delete(oldOrderItemMap.get(key));
            //库存相应的返回
            TbOrderItem tbOrderItem = oldOrderItemMap.get(key);
            stockService.changeStockNum(tbOrderItem.getItemId(), tbOrderItem.getItemQty().intValue(), updateOrder.getId(), "商品取消，库存退回");
        }

        TbOrder tbOrder = mybatisDao.selectByPrimaryKey(TbOrder.class, updateOrder.getId());
        tbOrder.setConsigneeName(updateOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(updateOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(updateOrder.getConsigneePhone());
        tbOrder.setOrderSource(updateOrder.getOrderSource());
        tbOrder.setPaymentMode(updateOrder.getPaymentMode());
        tbOrder.setOtherPaymentMode(updateOrder.getOtherPaymentMode());
        tbOrder.setMemberCode(updateOrder.getMemberCode());
        tbOrder.setRemark(updateOrder.getRemark());
        tbOrder.setOrderType(updateOrder.getOrderType());
        tbOrder.setOrderSource(updateOrder.getOrderSource());
        tbOrder.setXiajia(xiajia);
        totalAmount = totalAmount.subtract(updateOrder.getDiscountPrice());
        //订单总金额 如果是劵的 或者是餐桌计划的 则就是劵面金额 不用累计商品总金额
        if (tbOrder.getOrderType() == 2 || tbOrder.getOrderType() == 3) {
            tbOrder.setTotalAmount(updateOrder.getTotalPrice());
            totalAmount = updateOrder.getTotalPrice();
            tbOrder.setDiscountPrice(BigDecimal.ZERO);
        }
        else {
            tbOrder.setTotalAmount(totalAmount);
            tbOrder.setDiscountPrice(updateOrder.getDiscountPrice());
        }
        tbOrder.setAppointmentTime(updateOrder.getAppointmentTime());
        //订单更新时间
        tbOrder.setUpdateDate(mybatisDao.getSysdate());
        tbOrder.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        mybatisDao.update(tbOrder);

        //卡号，劵号，餐桌计划卡号
        if (StringUtils.trimToNull(updateOrder.getPaymentRelationNo()) != null && updateOrder.getOrderType() > 0) {
            TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
            tbOrderRelationExample.createCriteria()
                    .andOrderNoEqualTo(orderNo)
                    .andRefTypeEqualTo(OrderMapper.ORDER_PAY_RELATION_CODE);
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setRefValue(updateOrder.getPaymentRelationNo());
            mybatisDao.updateOneByExampleSelective(tbOrderRelation, tbOrderRelationExample);
        }

        //订单类型不是券或餐桌计划，修改订单的金额 修改方式 去掉之前的付款方式 重新生成（卡的钱先退回去）
        if (!(tbOrder.getOrderType() == 2 || tbOrder.getOrderType() == 3)) {
            RefundOrder refundOrder = new RefundOrder();
            refundOrder.setOrderNo(oldTbOrder.getOrderNo());
            refundOrder.setPaymentMode(oldTbOrder.getPaymentMode());
            refundOrder.setOtherPaymentMode(oldTbOrder.getOtherPaymentMode());
            refundOrder.setRemark("订单更新，金额变更");
            refundOrder(refundOrder);

            PayOrder payOrder = new PayOrder();
            payOrder.setOrderNo(oldTbOrder.getOrderNo());
            payOrder.setAmount(tbOrder.getTotalAmount());
            payOrder(payOrder);
        }

        //生成新的采购计划（订单状态不为待采购的才重新生成采购计划）
        if (!tbOrder.getOrderStatus().equals("1")) {
            String purchaseCode = DateUtils.getDateTimeString();
            for (UpdateOrder.OrderItem orderItem : changeOrderItems) {
                BigDecimal totalWeight = BigDecimal.ZERO;
                for (ItemModel cdItem : itemList) {
                    if (cdItem.getId().equals(orderItem.getOrderItemId())) {
                        totalWeight = new BigDecimal(cdItem.getSpec()).multiply(orderItem.getItemQty()).setScale(0, BigDecimal.ROUND_HALF_UP);
                    }
                }
                TbPurchase tbPurchase = new TbPurchase();
                tbPurchase.setPurchaseCode(purchaseCode);
                tbPurchase.setCreateDate(mybatisDao.getSysdate());
                tbPurchase.setPurchaseStatus(0);
                tbPurchase.setItemCode(orderItem.getItemCode());
                tbPurchase.setTotal(orderItem.getItemQty());
                tbPurchase.setTotalWeight(totalWeight);
                mybatisDao.insert(tbPurchase);
            }
        }
        return tbOrder;
    }

    /**
     * 删除订单
     *
     * @param id
     */
    @Transactional
    public void deleteOrder(Integer id) {
        mybatisDao.deleteByPrimaryKey(TbOrder.class, id);
    }

    /**
     * 取消订单
     *
     * @param id
     */
    @Transactional
    public void cancelOrder(Integer id) throws BusinessException {
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andIdEqualTo(id);
        TbOrder tbOrder = mybatisDao.selectOneByExample(tbOrderExample);
        //订单取消状态
        tbOrder.setOrderStatus("9");
        mybatisDao.updateByModel(tbOrder);

        //查询订单的原商品信息
        TbOrderItemExample tbOrderItemExample = new TbOrderItemExample();
        tbOrderItemExample.createCriteria().andOrderNoEqualTo(tbOrder.getOrderNo());
        List<TbOrderItem> tbOrderItems = mybatisDao.selectByExample(tbOrderItemExample);
        for (TbOrderItem tbOrderItem : tbOrderItems) {
            stockService.changeStockNum(tbOrderItem.getItemId(), tbOrderItem.getItemQty().intValue(), tbOrder.getId(), "订单取消退回商品");
        }

        //如果是卡类订单或者是餐桌计划订单(将钱退回卡里面)
        if (tbOrder.getOrderType() == 1) {
            RefundOrder refundOrder = new RefundOrder();
            refundOrder.setOrderNo(tbOrder.getOrderNo());
            refundOrder.setPaymentMode(tbOrder.getPaymentMode());
            refundOrder.setOtherPaymentMode(tbOrder.getOtherPaymentMode());
            refundOrder.setRemark("订单更新，金额变更");
            refundOrder(refundOrder);
        }
        //如果是劵 则将劵变为可用状态
        else if (tbOrder.getOrderType() == 2) {
            //获取劵号
            TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
            tbOrderRelationExample.createCriteria()
                    .andOrderNoEqualTo(tbOrder.getOrderNo())
                    .andRefTypeEqualTo(OrderMapper.ORDER_PAY_RELATION_CODE);
            TbOrderRelation tbOrderRelation = mybatisDao.selectOneByExample(tbOrderRelationExample);

            CdCouponExample cdCouponExample = new CdCouponExample();
            cdCouponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
            CdCoupon updateCdCoupon = new CdCoupon();
            updateCdCoupon.setIsUsed(0);
            mybatisDao.updateOneByExampleSelective(updateCdCoupon, cdCouponExample);

            //删除原来的订单付款记录
            TbTradeRecordExample tradeRecordExample = new TbTradeRecordExample();
            tradeRecordExample.createCriteria().andTradeNoEqualTo(tbOrder.getOrderNo())
                    .andTradeTypeEqualTo("COUPON");
            mybatisDao.deleteByExample(tradeRecordExample);
        }
        //如果是餐桌计划 则将已送次数-1 金额回退回去
        else if (tbOrder.getOrderType() == 3) {
            //获取餐桌计划卡号
            TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
            tbOrderRelationExample.createCriteria()
                    .andOrderNoEqualTo(tbOrder.getOrderNo())
                    .andRefTypeEqualTo(OrderMapper.ORDER_PAY_RELATION_CODE);
            TbOrderRelation tbOrderRelation = mybatisDao.selectOneByExample(tbOrderRelationExample);

            //删除原来的订单付款记录
            TbTradeRecordExample tradeRecordExample = new TbTradeRecordExample();
            tradeRecordExample.createCriteria().andTradeNoEqualTo(tbOrder.getOrderNo())
                    .andTradeTypeEqualTo("PLAN_CARD");
            TbTradeRecord tradeRecord = mybatisDao.selectOneByExample(tradeRecordExample);
            mybatisDao.deleteByExample(tradeRecordExample);

            //将餐桌计划卡里面的钱回退回去
            CdCouponExample couponExample = new CdCouponExample();
            couponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
            CdCoupon coupon = mybatisDao.selectOneByExample(couponExample);
            coupon.setUserPrice(coupon.getUserPrice().add(tradeRecord.getAmount()));
            mybatisDao.update(coupon);

            //餐桌计划已送次数回退一个
            TbOrderRefExample tbOrderRefExample = new TbOrderRefExample();
            tbOrderRefExample.createCriteria()
                    .andOrderNoEqualTo(tbOrder.getOrderNo())
                    .andRefTypeEqualTo("ORDER_TABLE_PLAN");
            List<TbOrderRef> tbOrderRefs = mybatisDao.selectByExample(tbOrderRefExample);
            for (TbOrderRef tbOrderRef : tbOrderRefs) {
                TbTablePlan tablePlan = mybatisDao.selectByPrimaryKey(TbTablePlan.class, Integer.parseInt(tbOrderRef.getRefValue()));
                tablePlan.setSendValue(tablePlan.getSendValue() - 1);
                mybatisDao.update(tablePlan);
            }
        }
    }

    @Transactional
    public void updateOrderStatus(String orderNo, String orderStatus) {
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus(orderStatus);
        mybatisDao.updateOneByExampleSelective(tbOrder, tbOrderExample);
    }

    @Transactional
    public void updateOrderStatus(Integer id, String orderStatus) {
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andIdEqualTo(id);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderStatus(orderStatus);
        mybatisDao.updateOneByExampleSelective(tbOrder, tbOrderExample);
    }

    /**
     * 订单退款／冲销
     *
     * @param refundOrder
     */
    @Transactional
    public void refundOrder(RefundOrder refundOrder) {
        //删除原来的订单付款记录
        TbTradeRecordExample tradeRecordExample = new TbTradeRecordExample();
        tradeRecordExample.createCriteria().andTradeNoEqualTo(refundOrder.getOrderNo())
                .andTradeTypeEqualTo("CARD");
        TbTradeRecord tradeRecord = mybatisDao.selectOneByExample(tradeRecordExample);
        mybatisDao.deleteByExample(tradeRecordExample);
        //删除其他的付款方式
        tradeRecordExample.createCriteria().andTradeNoEqualTo(refundOrder.getOrderNo())
                .andTradeTypeEqualTo(String.valueOf(refundOrder.getOtherPaymentMode()));
        mybatisDao.deleteByExample(tradeRecordExample);
        //如果是卡 将金额回退回去
        if (refundOrder.getPaymentMode() == 5) {
            //查找卡号
            TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
            tbOrderRelationExample.createCriteria()
                    .andOrderNoEqualTo(refundOrder.getOrderNo())
                    .andRefTypeEqualTo(OrderMapper.ORDER_PAY_RELATION_CODE);
            TbOrderRelation tbOrderRelation = mybatisDao.selectOneByExample(tbOrderRelationExample);
            CdCouponExample couponExample = new CdCouponExample();
            couponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
            CdCoupon coupon = mybatisDao.selectOneByExample(couponExample);
            //订单的钱回退回去
            coupon.setUserPrice(coupon.getUserPrice().add(tradeRecord.getAmount()));
            mybatisDao.update(coupon);
        }
    }

    /**
     * 支付订单
     *
     * @param payOrder
     */
    @Transactional
    public void payOrder(PayOrder payOrder) throws IllegalArgumentException {
        TbOrderRelation tbOrderRelation = new TbOrderRelation();
        tbOrderRelation.setRefType(OrderMapper.ORDER_PAY_RELATION_CODE);
        tbOrderRelation.setOrderNo(payOrder.getOrderNo());
        tbOrderRelation = mybatisDao.selectOneByModel(tbOrderRelation);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderNo(payOrder.getOrderNo());
        tbOrder = mybatisDao.selectOneByModel(tbOrder);
        Integer payStatus = 0;
        //卡劵订单才记录扣款记录
        if (tbOrder.getOrderType() > 0) {
            CdCoupon cdCoupon = new CdCoupon();
            cdCoupon.setCouponNumber(tbOrderRelation.getRefValue());
            cdCoupon = mybatisDao.selectOneByModel(cdCoupon);
            //卡类订单
            if (tbOrder.getOrderType() == 1) {
                BigDecimal amount = BigDecimal.ZERO;
                // 卡内支付
                if (cdCoupon != null &&
                        cdCoupon.getUserPrice() != null &&
                        tbOrder.getTotalAmount() != null &&
                        cdCoupon.getUserPrice().compareTo(tbOrder.getTotalAmount()) < 0) {
                    if (tbOrder.getOtherPaymentMode() == null) {
                        throw new IllegalArgumentException("卡内余额不足，请充值或添加附加付款方式");
                    }
                    // 扣除卡内所有金额，其余款项由附加付款方式支付
                    amount = cdCoupon.getUserPrice();
                    CdCouponExample cdCouponExample = new CdCouponExample();
                    cdCouponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
                    CdCoupon updateCdCoupon = new CdCoupon();
                    updateCdCoupon.setUserPrice(BigDecimal.ZERO);
                    updateCdCoupon.setIsUsed(1);
                    mybatisDao.updateOneByExampleSelective(updateCdCoupon, cdCouponExample);
                    //扣卡金额
                    TbTradeRecord tbTradeRecord = new TbTradeRecord();
                    tbTradeRecord.setAmount(amount);
                    tbTradeRecord.setCreateTime(mybatisDao.getSysdate());
                    tbTradeRecord.setTradeNo(payOrder.getOrderNo());
                    //卡类
                    tbTradeRecord.setTradeType("CARD");
                    mybatisDao.insert(tbTradeRecord);
                    // 代付金额
                    TbTradeRecord otherPayTbTradeRecord = new TbTradeRecord();
                    otherPayTbTradeRecord.setAmount(tbOrder.getTotalAmount().subtract(cdCoupon.getUserPrice()).multiply(new BigDecimal(-1)));
                    otherPayTbTradeRecord.setCreateTime(mybatisDao.getSysdate());
                    otherPayTbTradeRecord.setTradeNo(payOrder.getOrderNo());
                    otherPayTbTradeRecord.setTradeType(String.valueOf(tbOrder.getOtherPaymentMode()));
                    otherPayTbTradeRecord.setRemark("卡内金额不足，由其它支付方式代付");
                    mybatisDao.insert(otherPayTbTradeRecord);
                    payStatus = 2;//待结算
                }
                else {
                    amount = cdCoupon.getUserPrice().subtract(tbOrder.getTotalAmount());
                    CdCouponExample cdCouponExample = new CdCouponExample();
                    cdCouponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
                    CdCoupon updateCdCoupon = new CdCoupon();
                    updateCdCoupon.setUserPrice(amount);
                    updateCdCoupon.setIsUsed(1);
                    mybatisDao.updateOneByExampleSelective(updateCdCoupon, cdCouponExample);
                    TbTradeRecord tbTradeRecord = new TbTradeRecord();
                    tbTradeRecord.setAmount(payOrder.getAmount());
                    tbTradeRecord.setCreateTime(mybatisDao.getSysdate());
                    tbTradeRecord.setTradeNo(payOrder.getOrderNo());
                    //卡类
                    tbTradeRecord.setTradeType("CARD");
                    mybatisDao.insert(tbTradeRecord);
                    payStatus = 1;//已支付
                }
            }
            //劵类订单
            else if (tbOrder.getOrderType() == 2) {
                Date now = mybatisDao.getSysdate();
                if (cdCoupon.getBeginTime() != null && now.before(cdCoupon.getBeginTime())) {
                    throw new IllegalArgumentException("未到此优惠券的使用日期，请在优惠券使用期限内使用此优惠券");
                }
                if (cdCoupon.getEndTime() != null && now.after(cdCoupon.getEndTime())) {
                    throw new IllegalArgumentException("此优惠券已过期");
                }
                if (cdCoupon.getIsUseful() == 0) {
                    throw new IllegalArgumentException("无效的优惠券");
                }
                if (cdCoupon.getIsUsed() == 1) {
                    throw new IllegalArgumentException("此优惠券已被使用，请选择其它优惠券");
                }
                CdCouponExample cdCouponExample = new CdCouponExample();
                cdCouponExample.createCriteria().andCouponNumberEqualTo(tbOrderRelation.getRefValue());
                CdCoupon updateCdCoupon = new CdCoupon();
                updateCdCoupon.setIsUsed(1);
                //添加劵使用时间
                updateCdCoupon.setUsefulDate(com.xmomen.module.wx.util.DateUtils.getNowDate());
                mybatisDao.updateOneByExampleSelective(updateCdCoupon, cdCouponExample);
                TbTradeRecord tbTradeRecord = new TbTradeRecord();
                tbTradeRecord.setAmount(payOrder.getAmount());
                tbTradeRecord.setCreateTime(mybatisDao.getSysdate());
                tbTradeRecord.setTradeNo(payOrder.getOrderNo());
                //劵类
                tbTradeRecord.setTradeType("COUPON");
                mybatisDao.insert(tbTradeRecord);
                payStatus = 1;//已支付
            }
            //餐桌计划订单
            else if (tbOrder.getOrderType() == 3) {
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
                //餐桌计划卡
                tbTradeRecord.setTradeType("PLAN_CARD");
                mybatisDao.insert(tbTradeRecord);
                //已支付
                payStatus = 1;
            }
        }
        TbOrderExample tbOrderExample = new TbOrderExample();
        tbOrderExample.createCriteria().andOrderNoEqualTo(payOrder.getOrderNo());
        TbOrder tbOrder1 = new TbOrder();
        tbOrder1.setPayStatus(payStatus);
        mybatisDao.updateOneByExampleSelective(tbOrder1, tbOrderExample);
    }

    /**
     * 更新订单总箱数
     *
     * @param orderNo
     * @param totalBox
     */
    @Transactional
    public void updateTotalBox(String orderNo, int totalBox) {
        TbOrderExample orderExample = new TbOrderExample();
        orderExample.createCriteria()
                .andOrderNoEqualTo(orderNo);
        TbOrder order = new TbOrder();
        order.setTotalBoxNum(totalBox);
        mybatisDao.updateOneByExampleSelective(order, orderExample);
    }

    /**
     * 二次配送审核
     *
     * @param id
     * @param auditStatusCd
     */
    @Transactional
    public void twoSendOrder(int id, int auditStatusCd) {
        TbOrder order = this.mybatisDao.selectByPrimaryKey(TbOrder.class, id);
        order.setIsTwoSend(auditStatusCd);
        order.setTwoSendAuditDate(mybatisDao.getSysdate());
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        order.setTwoSendAuditUserId(userId);
        //审核通过
        if (auditStatusCd == 1) {
            //将订单状态改为待配送 同时清空快递员的配送信息
            order.setExpressMemberId(0);
            order.setOrderStatus("12");
        }
        //审核不通过
        else {

        }
        this.mybatisDao.save(order);
    }

    @Transactional
    public TbOrder createWxOrder(WxCreateOrder createOrder) throws Exception {
        String orderNo = createOrder.getOrderNo();
        if (StringUtils.isEmpty(orderNo)) {
            orderNo = DateUtils.getDateTimeString();
        }
        createOrder.setPaymentMode(null);
        createOrder.setOrderSource(1);

        TbOrder tbOrder = new TbOrder();
        // 订单新建 未支付状态
        tbOrder.setOrderStatus("0");
        tbOrder.setPayStatus(0);//待支付
        tbOrder.setTransportMode(1);// 默认快递

        List<Integer> itemIdList = new ArrayList<Integer>();
        boolean normalOrder = true;
        if (createOrder.getOrderType() == 2 && StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null) {
            normalOrder = false;
            CouponModel couponModel = couponService.getCouponModel(createOrder.getPaymentRelationNo());
            if (couponModel == null || couponModel.getCouponType() != 2) {
                throw new BusinessException("无效的券!");
            }
            tbOrder.setCompanyId(couponModel.getCompanyId());
            tbOrder.setManagerId(couponModel.getManagerId());
            List<CouponRelationItem> items = couponModel.getRelationItemList();
            List<WxCreateOrder.OrderItem> orderList = new ArrayList<WxCreateOrder.OrderItem>();
            for (CouponRelationItem item : items) {
                WxCreateOrder.OrderItem orderItem = new WxCreateOrder.OrderItem();
                orderItem.setOrderItemId(item.getItemId());
                orderItem.setItemQty(item.getItemNumber());
                orderList.add(orderItem);
            }
            createOrder.setOrderItemList(orderList);
            //券下单的总价格就是券的价格
            createOrder.setTotalPrice(couponModel.getCouponValue());
        }
        if (CollectionUtils.isEmpty(createOrder.getOrderItemList())) {
            throw new IllegalArgumentException("订单不能为空!");
        }
        for (WxCreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
            itemIdList.add(orderItem.getOrderItemId());
        }
        ItemQuery itemQuery = new ItemQuery();
        Integer[] array = new Integer[itemIdList.size()];
        itemQuery.setIds(itemIdList.toArray(array));
        List<ItemModel> itemList = itemService.queryItemList(itemQuery);
        BigDecimal totalAmount = BigDecimal.ZERO;
        int xiajia = 0;
        for (ItemModel cdItem : itemList) {
            for (WxCreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
                //查看商品是否下架了
                if (cdItem.getSellStatus() == 0) {
                    xiajia = 1;
                }
                if (cdItem.getId().equals(orderItem.getOrderItemId())) {
                    //判断库存是否有
                    boolean flag = stockService.checkStock(orderItem.getOrderItemId(), orderItem.getItemQty().intValue());
                    //库存不足
                    if (!flag) {
                        throw new BusinessException("商品：" + cdItem.getItemName() + "库存不足。下单失败");
                    }
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setOrderNo(orderNo);
                    tbOrderItem.setItemCode(cdItem.getItemCode());
                    tbOrderItem.setItemId(cdItem.getId());
                    tbOrderItem.setItemName(cdItem.getItemName());
                    tbOrderItem.setItemQty(orderItem.getItemQty());
                    tbOrderItem.setItemUnit(cdItem.getPricingManner());
                    if (cdItem.getDiscountPrice() != null) {
                        tbOrderItem.setItemPrice(cdItem.getDiscountPrice());
                    }
                    else {
                        tbOrderItem.setItemPrice(cdItem.getSellPrice());
                    }
                    totalAmount = totalAmount.add(tbOrderItem.getItemPrice().multiply(orderItem.getItemQty()));
                    tbOrderItem.setCreateDate(mybatisDao.getSysdate());
                    tbOrderItem.setCreateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                    tbOrderItem.setUpdateDate(mybatisDao.getSysdate());
                    tbOrderItem.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                    mybatisDao.insert(tbOrderItem);
                }
            }
        }

        if (createOrder.getAppointmentTime() != null) {
            tbOrder.setAppointmentTime(createOrder.getAppointmentTime());
        }
        else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date appointmentTime = calendar.getTime();
            tbOrder.setAppointmentTime(appointmentTime);
        }
        tbOrder.setConsigneeName(createOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(createOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(createOrder.getConsigneePhone());

        MemberAddressQuery memberAddressQuery = new MemberAddressQuery();
        memberAddressQuery.setCdMemberId(String.valueOf(createOrder.getCreateUserId()));
        if (StringUtils.isEmpty(createOrder.getConsigneeName()) || StringUtils.isEmpty(createOrder.getConsigneeAddress())
                || StringUtils.isEmpty(createOrder.getConsigneePhone())) {
            List<MemberAddressModel> addresses = memberAddressService.getMemberAddressModels(memberAddressQuery);
            if (addresses != null) {
                int size = addresses.size();
                for (int i = 0; i < size; i++) {
                    MemberAddressModel address = addresses.get(i);
                    if (i == 0) {
                        tbOrder.setConsigneeName(address.getName());
                        tbOrder.setConsigneeAddress(address.getFullAddress());
                        tbOrder.setConsigneePhone(address.getMobile());
                    }
                    if (address.getIsDefault()) {
                        tbOrder.setConsigneeName(address.getName());
                        tbOrder.setConsigneeAddress(address.getFullAddress());
                        tbOrder.setConsigneePhone(address.getMobile());
                        break;
                    }
                }
            }
        }

        tbOrder.setCreateTime(mybatisDao.getSysdate());
        tbOrder.setPaymentMode(null);
        tbOrder.setOrderType(createOrder.getOrderType());
        if (StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null && createOrder.getOrderType() == 2) {
            tbOrder.setPaymentMode(7);
            //券订单代采购状态
            tbOrder.setOrderStatus("1");
        }
        else {
            tbOrder.setOrderType(0);//设置为常规订单
        }
        tbOrder.setOtherPaymentMode(createOrder.getOtherPaymentMode());

        tbOrder.setMemberCode(String.valueOf(createOrder.getCreateUserId()));
        tbOrder.setRemark(createOrder.getRemark());

        tbOrder.setOrderNo(orderNo);
        tbOrder.setOrderSource(createOrder.getOrderSource());
        tbOrder.setCreateUserId(createOrder.getCreateUserId());
        tbOrder.setXiajia(xiajia);


        totalAmount = totalAmount.subtract(createOrder.getDiscountPrice() == null ? BigDecimal.ZERO : createOrder.getDiscountPrice());
        //订单总金额 如果是劵的 则就是劵面金额 不用累计商品总金额
        if ((tbOrder.getOrderType() == 2 && StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null)) {
            tbOrder.setTotalAmount(createOrder.getTotalPrice());
            totalAmount = createOrder.getTotalPrice();
            tbOrder.setDiscountPrice(BigDecimal.ZERO);
            //生成收货码
            TbOrderRef orderRef = new TbOrderRef();
            orderRef.setOrderNo(orderNo);
            orderRef.setRefType("SHOU_HUO_NO");
            orderRef.setRefValue(String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
            mybatisDao.insert(orderRef);
        }
        else {
            tbOrder.setTotalAmount(totalAmount);
            tbOrder.setDiscountPrice(createOrder.getDiscountPrice());
        }
        tbOrder.setUpdateDate(mybatisDao.getSysdate());
        tbOrder.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        tbOrder = mybatisDao.insertByModel(tbOrder);

        //扣除订单相应的产品库存
        for (WxCreateOrder.OrderItem orderItem : createOrder.getOrderItemList()) {
            //扣除库存
            stockService.changeStockNum(orderItem.getOrderItemId(), 0 - orderItem.getItemQty().intValue(), tbOrder.getId(), "后台订单扣除商品");
        }

        // tbOrderNo validation
        /*if (StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null && createOrder.getOrderType() > 0) {
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setOrderNo(orderNo);
            tbOrderRelation.setRefType(OrderMapper.ORDER_PAY_RELATION_CODE);// 订单支付关系
            tbOrderRelation.setRefValue(createOrder.getPaymentRelationNo());
            mybatisDao.insert(tbOrderRelation);
        }*/
        if ((tbOrder.getOrderType() == 2 && StringUtils.trimToNull(createOrder.getPaymentRelationNo()) != null)) {
            //支付的时候再建立支付关系
            TbOrderRelation tbOrderRelation = new TbOrderRelation();
            tbOrderRelation.setOrderNo(orderNo);
            tbOrderRelation.setRefType(OrderMapper.ORDER_PAY_RELATION_CODE);// 订单支付关系
            tbOrderRelation.setRefValue(createOrder.getPaymentRelationNo());
            mybatisDao.insert(tbOrderRelation);

            PayOrder payOrder = new PayOrder();
            payOrder.setOrderNo(tbOrder.getOrderNo());
            payOrder.setAmount(totalAmount);
            payOrder(payOrder);
        }
        // 非券类订单则要将对应的物品从购物车只移除
        String userToken = String.valueOf(createOrder.getCreateUserId());
        if (normalOrder) {
            cartServcie.removeItems(userToken, itemIdList);
        }
        return tbOrder;
    }

    @Transactional
    public Boolean payWxOrder(PayOrderModel payOrderModel) throws Exception {

        Integer orderId = payOrderModel.getOrderId();

        TbOrder tbOrder = mybatisDao.selectByPrimaryKey(TbOrder.class, orderId);
        Integer orderType = payOrderModel.getOrderType();
        if (orderType == null || (orderType != 1)) {
            //货到付款订单
            tbOrder.setOrderStatus("1");
            tbOrder.setPayStatus(1);
            tbOrder.setOrderType(0);//常规订单,货到付款类型
            tbOrder.setPaymentMode(4);//支付类型为物流公司代收

            Integer userId = null;
            try {
                userId = Integer.valueOf(tbOrder.getMemberCode());
            } catch (Exception e) {
                userId = tbOrder.getCreateUserId();
            }
            CdMember member = mybatisDao.selectByPrimaryKey(CdMember.class, userId);
            if (member != null) {
                tbOrder.setCompanyId(member.getCdCompanyId());
                tbOrder.setManagerId(member.getCdUserId());
            }
            mybatisDao.update(tbOrder);
            return Boolean.TRUE;
        }
        String paymentNo = payOrderModel.getPaymentNo();
        if (StringUtils.isEmpty(paymentNo)) {
            throw new BusinessException("卡号不能为空!");
        }
        CdCoupon cdCouponQuery = new CdCoupon();
        cdCouponQuery.setCouponNumber(paymentNo);
        CdCoupon cdCoupon = mybatisDao.selectOneByModel(cdCouponQuery);
        if (cdCoupon == null || cdCoupon.getCouponType() != 1) {
            throw new BusinessException("该卡不存在!");
        }

        MyOrderQuery myOrderQuery = new MyOrderQuery();
        myOrderQuery.setOrderId(orderId);
        OrderDetailModel orderDetailModel = myOrderService.getOrderDetail(myOrderQuery);
        List<OrderProductItem> itemList = orderDetailModel.getProducts();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderProductItem cdItem : itemList) {
            BigDecimal price = cdItem.getItemPrice();
            totalAmount = totalAmount.add(price.multiply(cdItem.getItemQty()));

        }
        if (cdCoupon.getUserPrice().compareTo(totalAmount) < 0) {
            throw new BusinessException("卡内余额不足，请充值或选用其他付款方式!");
        }
        //设置为卡支付订单
        tbOrder.setPaymentMode(5);
        tbOrder.setOrderType(1);
        tbOrder.setOrderStatus("1");

        tbOrder.setCompanyId(cdCoupon.getCdCompanyId());
        tbOrder.setManagerId(cdCoupon.getCdUserId());
        mybatisDao.update(tbOrder);

        TbOrderRelation tbOrderRelation = new TbOrderRelation();
        tbOrderRelation.setOrderNo(tbOrder.getOrderNo());
        tbOrderRelation.setRefType(OrderMapper.ORDER_PAY_RELATION_CODE);// 订单支付关系
        tbOrderRelation.setRefValue(payOrderModel.getPaymentNo());
        mybatisDao.insert(tbOrderRelation);

        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(orderDetailModel.getOrderNo());
        payOrder.setAmount(totalAmount);
        payOrder(payOrder);


        return true;
    }

    public List<ProductModel> getCouponItems(String couponNo) {
        CouponModel couponModel = couponService.getCouponModel(couponNo);
        if (couponModel == null || couponModel.getCouponType() != 2) {
            throw new IllegalArgumentException("无效的券!");
        }
        List<CouponRelationItem> items = couponModel.getRelationItemList();
        Map<Integer, Integer> itemInfoMap = new HashMap<Integer, Integer>();
        List<Integer> itemIds = new ArrayList<Integer>();
        for (CouponRelationItem item : items) {
            itemInfoMap.put(item.getItemId(), item.getItemNumber().intValue());
            itemIds.add(item.getItemId());
        }
        List<ProductModel> productModels = productService.getProducts(itemIds);
        for (ProductModel productModel : productModels) {
            productModel.setItemQty(itemInfoMap.get(productModel.getId()));
        }
        return productModels;
    }
}
