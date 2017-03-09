package com.xmomen.module.base.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.mapper.ExpressMapper;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderRef;
import com.xmomen.module.order.entity.TbOrderRefExample;
import com.xmomen.module.order.entity.TbOrderRelation;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.report.model.ExpressReport;
import com.xmomen.module.report.model.OrderDeliveryReport;
import com.xmomen.module.wx.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            mybatisDao.getSqlSessionTemplate().update(ExpressMapper.ExpressMapperNameSpace + "cancelDespatch", orderNo);
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
     * 查询已分配未提货的订单
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<OrderModel> getOrderNoDespatchReportList(OrderQuery orderQuery, Integer limit, Integer offset){
        return (Page<OrderModel>) mybatisDao.selectPage(ExpressMapper.ExpressMapperNameSpace + "getOrderNoDespatchReportList", orderQuery, limit, offset);
    }
    
    /**
     * 查询分配未提货的订单(导出)
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public List<OrderDeliveryReport> getOrderNoDespatchReportList(OrderQuery orderQuery){
        return mybatisDao.getSqlSessionTemplate().selectList(ExpressMapper.ExpressMapperNameSpace + "getOrderNoDespatchReportList", orderQuery);
    }
	
	/**
	 * 发运操作
	 * 
	 * @param boxNo 箱号
	 */
	@Transactional
	public void takeDelivery(String boxNo){
		//查找箱子是否扫描过
		TbOrderRef orderRefBoxNo = new TbOrderRef();
		orderRefBoxNo.setRefType("TAKE_DELIVERY_BOXNO");
		orderRefBoxNo.setRefValue(boxNo);
		orderRefBoxNo = mybatisDao.selectOneByModel(orderRefBoxNo);
		AssertExt.isNull(orderRefBoxNo, "该包裹已经提货，不能重复提货！");
		
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
        AssertExt.notNull(order.getDespatchExpressId(), "订单未分配不能提货！");
        String orderStatus = order.getOrderStatus();
        AssertExt.isTrue(orderStatus.equals("4"), "订单状态不是待出库，不能提货！");
		//判断是否是快递商的货
		CdExpress express = mybatisDao.selectByPrimaryKey(CdExpress.class,order.getDespatchExpressId());
		AssertExt.notNull(express,"分配的快递商不存在了，请确认！");
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		AssertExt.isTrue(username.equals(express.getExpressCode()), "该包裹分配的快递商不符合，不能提货！");
		int totalBoxNum = order.getTotalBoxNum() ;
		int scanBoxNum = order.getExpressScanBoxNum() + 1;
		order.setExpressScanBoxNum(scanBoxNum);
		//箱号加入order_ref 记录下扫描的箱子编号
		orderRefBoxNo = new TbOrderRef();
		orderRefBoxNo.setOrderNo(tbOrderRelation.getOrderNo());
		orderRefBoxNo.setRefType("TAKE_DELIVERY_BOXNO");
		orderRefBoxNo.setRefValue(boxNo);
		mybatisDao.insert(orderRefBoxNo);
		
		if(totalBoxNum == scanBoxNum){
			//更新订单为待配送
			order.setOrderStatus("12");
			//更新出库时间
			order.setOutDate(DateUtils.getNowDate());
			//订单加入order_ref 表示整个订单的箱子全部扫描
			TbOrderRef orderRef = new TbOrderRef();
			orderRef.setOrderNo(tbOrderRelation.getOrderNo());
			orderRef.setRefType("TAKE_DELIVERY");
			orderRef.setRefValue(express.getExpressCode());
			TbOrderRef orderRefDB = mybatisDao.selectOneByModel(orderRef);
			AssertExt.isTrue(orderRefDB == null, "该包裹已经提货，不能重复提货！");
			mybatisDao.insert(orderRef);
		}
		mybatisDao.update(order);
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
		AssertExt.isTrue(username.equals(express.getExpressCode()), "该包裹分配的快递商不符合，不能取消提货！");
		
		//更新订单为待出库
		order.setOrderStatus("4");
		order.setExpressScanBoxNum(0);
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
		//删除扫描的箱子 
		TbOrderRefExample orderRefBoxNo = new TbOrderRefExample();
		orderRefBoxNo.createCriteria().andOrderNoEqualTo(orderNo)
		.andRefTypeEqualTo("TAKE_DELIVERY_BOXNO");
		mybatisDao.deleteByExample(orderRefBoxNo);
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
