package com.xmomen.module.plan.service;

import com.xmomen.module.plan.model.CreateTablePlan;
import com.xmomen.module.plan.model.UpdateTablePlan;

public interface TablePlanSercvice {
	public void createTablePlan(CreateTablePlan createTablePlan);
	
	public void updateTablePlan(Integer id,UpdateTablePlan updateTablePlan);
	
	public void delete(Integer id);
	
	/**
	 * 生成餐桌计划订单
	 */
	public void createTablePlanOrder();
}
