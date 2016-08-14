package com.xmomen.module.base.service;

import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.model.ExpressTask;

public interface ExpressService {
	public void createExpress(CdExpress createExpress);
	
	public void updateExpress(Integer id,CdExpress updateExpress);
	
	public void delete(Integer id);
	
	public void dispatchExpress(ExpressTask expressTask);
	
	public void cancelExpress(String[] orderNoList);
	
	public void takeDelivery(String orderNo);
}	
