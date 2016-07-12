package com.xmomen.module.base.service;

import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.entity.CdExpressMember;
import com.xmomen.module.base.model.ExpressTask;

public interface ExpressMemberService {
	public void createExpressMember(CdExpressMember createExpress);
	
	public void updateExpressMember(Integer id,CdExpressMember updateExpress);
	
	public void delete(Integer id);
	
}	
