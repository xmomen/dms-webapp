package com.xmomen.module.base.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderRef;

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
	 * 发运操作
	 */
	@Transactional
	public void takeDelivery(String orderNo){
		TbOrder order = new TbOrder();
        order.setOrderNo(orderNo);
        order = mybatisDao.selectOneByModel(order);
        AssertExt.notNull(order.getExpressMemberId(), "订单未分配不能提货！");
		//判断是否是快递商的货
		CdExpress express = mybatisDao.selectByPrimaryKey(CdExpress.class,order.getExpressMemberId());
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		AssertExt.isTrue(username.equals(express.getExpressCode()), "该包裹分配的快递商不符合，不能提货！");
		//判断是否已经扫描过
		
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

}
