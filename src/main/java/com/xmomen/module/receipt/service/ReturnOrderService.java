package com.xmomen.module.receipt.service;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderExample;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.entity.TbOrderRef;
import com.xmomen.module.order.entity.TbOrderRelation;
import com.xmomen.module.order.entity.TbReturnOrder;
import com.xmomen.module.order.entity.TbReturnOrderExample;
import com.xmomen.module.order.entity.TbReturnOrderItem;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.ReturnOrder;
import com.xmomen.module.receipt.mapper.ReturnOrderMapper;
import com.xmomen.module.receipt.model.ReturnOrderModel;
import com.xmomen.module.receipt.model.ReturnOrderQuery;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class ReturnOrderService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    ItemService itemService;

    /**
     * 查询订单
     *
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<ReturnOrderModel> getReturnOrderList(ReturnOrderQuery orderQuery, Integer limit, Integer offset) {
        return (Page<ReturnOrderModel>) mybatisDao.selectPage(ReturnOrderMapper.RETURN_ORDER_MAPPER_NAMESPACE + "getReturnOrderList", orderQuery, limit, offset);
    }

    /**
     * 查询订单
     *
     * @param orderQuery
     * @return
     */
    public List<ReturnOrderModel> getReturnOrderList(ReturnOrderQuery orderQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(ReturnOrderMapper.RETURN_ORDER_MAPPER_NAMESPACE + "getReturnOrderList", orderQuery);
    }
    

    /**
     * 订单退货
     *
     * @param returnOrder
     */
    @Transactional
    public String returnOrder(String orderNo,String itemIds) {
    	//判断订单状态
    	TbReturnOrderExample tbOrderExample = new TbReturnOrderExample();
    	tbOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
    	TbReturnOrder tbReturnOrder = mybatisDao.selectOneByExample(tbOrderExample);
    	if(tbReturnOrder != null){
    		return "订单不能退货，请联系客服人员";
    	}
        tbReturnOrder = new TbReturnOrder();
        tbReturnOrder.setOrderNo(orderNo);
        tbReturnOrder.setReturnStatus(10);//申请退货
        tbReturnOrder.setReturnTime(mybatisDao.getSysdate());
        tbReturnOrder = mybatisDao.insertByModel(tbReturnOrder);
        for (String  itemId : itemIds.split(",")) {
        	if(StringUtilsExt.isEmpty(itemId)){
        		continue;
        	}
            TbReturnOrderItem returnOrderItem = new TbReturnOrderItem();
            TbOrderItem orderItem = this.mybatisDao.selectByPrimaryKey(TbOrderItem.class, Integer.parseInt(itemId));
            returnOrderItem.setItemCode(orderItem.getItemCode());
            returnOrderItem.setReturnOrderId(tbReturnOrder.getId());
            returnOrderItem.setItemNumber(orderItem.getItemQty().intValue());
            returnOrderItem.setItemName(orderItem.getItemName());
            returnOrderItem.setIsNeed(1);
            mybatisDao.insert(returnOrderItem);
        }
        //更新原订单状态
        TbOrderExample orderExample = new TbOrderExample();
        orderExample.createCriteria()
                .andOrderNoEqualTo(orderNo);
        TbOrder order = new TbOrder();
        order.setOrderStatus("10");//申请退货
        mybatisDao.updateOneByExampleSelective(order, orderExample);
        return "退货申请提交成功，请等待客服审核。";
    }
    
    
    /**
     * 退货订单收货
     *
     * @param returnOrder
     */
    @Transactional
    public String shouhuoReturn(String orderNo,String phone,int expressId) {
    	//判断订单状态
    	TbReturnOrderExample tbOrderExample = new TbReturnOrderExample();
    	tbOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
    	TbReturnOrder tbReturnOrder = mybatisDao.selectOneByExample(tbOrderExample);
    	if(tbReturnOrder.getReturnStatus() != 11){
    		return "订单状态不为退货中，不能收货，请联系客服人员";
    	}
        tbReturnOrder.setReturnStatus(14);//退货运输中
        tbReturnOrder.setTakeGoodsDate(mybatisDao.getSysdate());
        tbReturnOrder.setTakeGoodsPhone(phone);
        tbReturnOrder.setTakeGoodsUserId(expressId);
        mybatisDao.update(tbReturnOrder);
        //更新原订单状态
        TbOrderExample orderExample = new TbOrderExample();
        orderExample.createCriteria()
                .andOrderNoEqualTo(orderNo);
        TbOrder order = new TbOrder();
        order.setOrderStatus("14");//退货运输中
        mybatisDao.updateOneByExampleSelective(order, orderExample);
        return "退货订单收货成功，请尽快送回仓库。";
    }
    
    /**
     * 退货订单审核
     * @param id
     * @param statusCd
     */
    @Transactional
    public void auditReturnOrder(int id,int statusCd){
    	TbReturnOrder returnOrder = this.mybatisDao.selectByPrimaryKey(TbReturnOrder.class, id);
    	returnOrder.setAuditStatus(statusCd);
    	returnOrder.setAuditDate(mybatisDao.getSysdate());
    	//退货中
    	returnOrder.setReturnStatus(11);
    	Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
    	returnOrder.setAuditUserId(userId);
    	this.mybatisDao.save(returnOrder);
    	 //更新原订单状态
        TbOrderExample orderExample = new TbOrderExample();
        orderExample.createCriteria()
                .andOrderNoEqualTo(returnOrder.getOrderNo());
        TbOrder order = new TbOrder();
        //退货中
        order.setOrderStatus("11");
        mybatisDao.updateOneByExampleSelective(order, orderExample);
    }
    
    
    /**
	 * 退货收货操作
	 * 
	 * @param boxNo 箱号
	 */
	@Transactional
	public ReturnOrderModel returnOrderShouhuo(String boxNo){
		//通过箱号查找订单号
		TbOrderRelation tbOrderRelation = new TbOrderRelation();
        tbOrderRelation.setRefType(OrderMapper.ORDER_PACKING_RELATION_CODE);
        tbOrderRelation.setRefValue(boxNo);
        tbOrderRelation = mybatisDao.selectOneByModel(tbOrderRelation);
        AssertExt.notNull(tbOrderRelation, "扫描的箱号不存在！请确认。");
		TbOrder order = new TbOrder();
        order.setOrderNo(tbOrderRelation.getOrderNo());
        order = mybatisDao.selectOneByModel(order);
        AssertExt.notNull(order, "订单不存在！");
        String orderStatus = order.getOrderStatus();
        AssertExt.isTrue(orderStatus.equals("14"), "订单状态不是退货运输中，不能收货！");
        
        TbReturnOrder returnOrder = new TbReturnOrder();
        returnOrder.setOrderNo(order.getOrderNo());
        returnOrder = mybatisDao.selectOneByModel(returnOrder);
        
        ReturnOrderModel returnOrderModel = new ReturnOrderModel();
        TbReturnOrderItem returnOrderItem = new TbReturnOrderItem();
        returnOrderItem.setReturnOrderId(returnOrder.getId());
        List<TbReturnOrderItem> returnOrderItemList = this.mybatisDao.selectByModel(returnOrderItem);
        returnOrderModel.setItemList(returnOrderItemList);
        returnOrderModel.setOrderNo(order.getOrderNo());
        return returnOrderModel;
	}
	
	/**
	 * 退货收货操作
	 * 
	 * @param boxNo 箱号
	 */
	@Transactional
	public void shouhuo(String orderNo){
		TbOrder order = new TbOrder();
        order.setOrderNo(orderNo);
        order = mybatisDao.selectOneByModel(order);
        AssertExt.notNull(order, "订单不存在！");
        String orderStatus = order.getOrderStatus();
        AssertExt.isTrue(orderStatus.equals("14"), "订单状态不是退货运输中，不能收货！");
        
        order.setOrderStatus("15");
        mybatisDao.update(order);
        
        TbReturnOrder returnOrder = new TbReturnOrder();
        returnOrder.setOrderNo(order.getOrderNo());
        returnOrder = mybatisDao.selectOneByModel(returnOrder);
        returnOrder.setReturnStatus(15);
        mybatisDao.update(returnOrder);
        //卡类订单进行退款
        if(order.getOrderType() == 1){
        	
        }
	}
    
}
