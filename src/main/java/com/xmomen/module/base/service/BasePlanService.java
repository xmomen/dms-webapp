package com.xmomen.module.base.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.base.model.CreatePlan;
import com.xmomen.module.base.model.PlanModel;
import com.xmomen.module.base.model.UpdatePlan;

public interface BasePlanService {
	public Page<PlanModel> getPlanList(String keyword,Integer limit, Integer offset);
	
	public CdPlan getPlan(Integer id);
	
	public CdPlan createPlan(CreatePlan createPlan);
	
	public CdPlan updatePlan(Integer id,UpdatePlan updatePlan);
	
	public void delete(Integer id);
}	
