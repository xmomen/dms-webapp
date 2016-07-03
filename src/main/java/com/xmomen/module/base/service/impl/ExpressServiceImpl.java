package com.xmomen.module.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.order.entity.TbOrder;

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
	public void cancelExpress(String[] orderNoList) {
		for (String orderNo : orderNoList) {
            TbOrder order = new TbOrder();
            order.setOrderNo(orderNo);
            order = mybatisDao.selectOneByModel(order);
            
            order.setDespatchExpressId(null);
            mybatisDao.update(order);
        }
	}

}
