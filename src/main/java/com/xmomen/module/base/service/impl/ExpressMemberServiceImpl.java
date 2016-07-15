package com.xmomen.module.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.entity.CdExpressMember;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.base.service.ExpressMemberService;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.order.entity.TbOrder;

@Service
public class ExpressMemberServiceImpl implements ExpressMemberService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createExpressMember(CdExpressMember createExpress) {
			mybatisDao.save(createExpress);
	}
	
	@Transactional
	public void updateExpressMember(Integer id, CdExpressMember updateExpress) {
		updateExpress.setId(id);
		mybatisDao.update(updateExpress);
	}
	
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdExpress.class, id);		
	}

}
