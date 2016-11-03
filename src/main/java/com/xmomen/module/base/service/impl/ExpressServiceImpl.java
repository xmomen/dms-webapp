package com.xmomen.module.base.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.mapper.ExpressMapper;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderRef;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.report.model.OrderDeliveryReport;

@Service
public class ExpressServiceImpl implements ExpressService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createExpress(CdExpress createExpress) {
			mybatisDao.save(createExpress);
	}
	@Transactional
	public void updateExpress(Integer id, CdExpress updateExpress) {
		updateExpress.setId(id);
		mybatisDao.update(updateExpress);
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdExpress.class, id);		
	}
	@Override
	@Transactional
	public void dispatchExpress(ExpressTask expressTask) {
		for (String orderNo : expressTask.getOrderNos()) {
            TbOrder order = new TbOrder();
            order.setOrderNo(orderNo);
            order = mybatisDao.selectOneByModel(order);
            
            order.setDespatchExpressId(expressTask.getExpressId());
            mybatisDao.update(order);
        }
		
	}
	@Override
	@Transactional
	public void cancelExpress(String[] orderNoList) {
		for (String orderNo : orderNoList) {
            TbOrder order = new TbOrder();
            order.setOrderNo(orderNo);
            order = mybatisDao.selectOneByModel(order);
            
            order.setDespatchExpressId(null);
            mybatisDao.update(order);
        }
	}
	
	
	/**
     * 查询已扫描的订单
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<OrderModel> getTakeDeliveryList(OrderQuery orderQuery, Integer limit, Integer offset){
        return (Page<OrderModel>) mybatisDao.selectPage(ExpressMapper.ExpressMapperNameSpace + "getOrderList", orderQuery, limit, offset);
    }
    
    /**
     * 查询已扫描的订单(导出)
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public List<OrderDeliveryReport> getTakeDeliveryReportList(OrderQuery orderQuery){
        return mybatisDao.getSqlSessionTemplate().selectList(ExpressMapper.ExpressMapperNameSpace + "getOrderReportList", orderQuery);
    }
	
	/**
	 * 发运操作
	 */
	@Transactional
	public void takeDelivery(String orderNo){
		TbOrder order = new TbOrder();
        order.setOrderNo(orderNo);
        order = mybatisDao.selectOneByModel(order);
        AssertExt.notNull(order, "订单不存在！");
        AssertExt.notNull(order.getDespatchExpressId(), "订单未分配不能提货！");
        String orderStatus = order.getOrderStatus();
        AssertExt.isTrue(orderStatus.equals("4"), "订单状态不是待出库，不能提货！");
		//判断是否是快递商的货
		CdExpress express = mybatisDao.selectByPrimaryKey(CdExpress.class,order.getDespatchExpressId());
		AssertExt.notNull(express,"分配的快递商不存在了，请确认！");
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		AssertExt.isTrue(username.equals(express.getExpressCode()), "该包裹分配的快递商不符合，不能提货！");
		//更新订单为待配送
		order.setOrderStatus("12");
		mybatisDao.update(order);
		//加入ref
		TbOrderRef orderRef = new TbOrderRef();
		orderRef.setOrderNo(orderNo);
		orderRef.setRefType("TAKE_DELIVERY");
		orderRef.setRefValue(express.getExpressCode());
		TbOrderRef orderRefDB = mybatisDao.selectOneByModel(orderRef);
		if(orderRefDB != null){
			mybatisDao.delete(orderRefDB);
		}
		mybatisDao.insert(orderRef);

	}
	
	/**
	 * 发运操作
	 */
	@Transactional
	public void unTakeDelivery(String orderNo){
		TbOrder order = new TbOrder();
        order.setOrderNo(orderNo);
        order = mybatisDao.selectOneByModel(order);
        AssertExt.notNull(order.getDespatchExpressId(), "订单未分配不能取消提货！");
		//判断是否是快递商的货
		CdExpress express = mybatisDao.selectByPrimaryKey(CdExpress.class,order.getDespatchExpressId());
		AssertExt.notNull(express,"分配的快递商不存在了，请确认！");
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		AssertExt.isTrue(username.equals(express.getExpressCode()), "该包裹分配的快递商不符合，不能提货！");
		
		//更新订单为待出库
		order.setOrderStatus("4");
		mybatisDao.update(order);
		//删除关系ref
		TbOrderRef orderRef = new TbOrderRef();
		orderRef.setOrderNo(orderNo);
		orderRef.setRefType("TAKE_DELIVERY");
		orderRef.setRefValue(express.getExpressCode());
		TbOrderRef orderRefDB = mybatisDao.selectOneByModel(orderRef);
		if(orderRefDB != null){
			mybatisDao.delete(orderRefDB);
		}		
	}
	
	/**
	 * 获取快递商提货的订单
	 */
	@Override
	public List<OrderModel> getTakeDeliveryList(OrderQuery query) {
		// TODO Auto-generated method stub      
		return  mybatisDao.getSqlSessionTemplate().selectList(ExpressMapper.ExpressMapperNameSpace + "getOrderList", query);
	}

}
