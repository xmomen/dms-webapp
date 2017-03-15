package com.xmomen.module.base.service;

import java.util.List;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.report.model.ExpressReport;
import com.xmomen.module.report.model.OrderDeliveryReport;

public interface ExpressService {
	public void createExpress(CdExpress createExpress);
	
	public void updateExpress(Integer id,CdExpress updateExpress);
	
	public void delete(Integer id);
	
	public void dispatchExpress(ExpressTask expressTask);
	
	public void cancelExpress(String[] orderNoList);
	
	public void takeDelivery(String orderNo);
	
	public void unTakeDelivery(String orderNo);
	
	public Page<OrderModel> getTakeDeliveryList(OrderQuery orderQuery, Integer limit, Integer offset);
	
	public List<OrderModel> getTakeDeliveryList(OrderQuery query);
	
	public List<OrderDeliveryReport> getTakeDeliveryReportList(OrderQuery query);
	
	public List<OrderDeliveryReport> getOrderNoDespatchReportList(OrderQuery query);
	
	public Page<OrderModel> getOrderNoDespatchReportList(OrderQuery orderQuery, Integer limit, Integer offset);
}	
