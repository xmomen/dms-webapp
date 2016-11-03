package com.xmomen.module.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.plan.entity.TbTablePlan;
import com.xmomen.module.plan.model.CreateTablePlan;
import com.xmomen.module.plan.model.UpdateTablePlan;
import com.xmomen.module.plan.service.TablePlanSercvice;

@Service
public class TablePlanSercviceImpl implements TablePlanSercvice {
	@Autowired
	MybatisDao mybatisDao;

	@Override
	@Transactional
	public void createTablePlan(CreateTablePlan createTablePlan) {
		for(TbTablePlan tablePlan :createTablePlan.getTablePlans()){
			tablePlan.setAuditStatus(1);
			tablePlan.setCdMemberId(createTablePlan.getCdMemberId());
			tablePlan.setConsigneeAddress(createTablePlan.getConsigneeAddress());
			tablePlan.setConsigneeName(createTablePlan.getConsigneeName());
			tablePlan.setConsigneePhone(createTablePlan.getConsigneePhone());
			tablePlan.setCouponNumber(createTablePlan.getCouponNumber());
			tablePlan.setSendValue(0);
			CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,tablePlan.getCdPlanId());
			tablePlan.setTotalSendValue(plan.getDeliverCount());
			mybatisDao.saveByModel(tablePlan);
		}
	}
	
	@Transactional
	public void updateTablePlan(Integer id,UpdateTablePlan updateTablePlan) {
		TbTablePlan tablePlan = new TbTablePlan();
		tablePlan.setId(id);
		tablePlan.setCdPlanId(updateTablePlan.getCdPlanId());
		tablePlan.setCdMemberId(updateTablePlan.getCdMemberId());
		tablePlan.setConsigneeAddress(updateTablePlan.getConsigneeAddress());
		tablePlan.setConsigneeName(updateTablePlan.getConsigneeName());
		tablePlan.setConsigneePhone(updateTablePlan.getConsigneePhone());
		tablePlan.setCouponNumber(updateTablePlan.getCouponNumber());
		CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,updateTablePlan.getCdPlanId());
		tablePlan.setTotalSendValue(plan.getDeliverCount());
		mybatisDao.saveByModel(tablePlan);
	}
	
	@Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(TbTablePlan.class, id);
    }

	/**
	 * 生成餐桌计划订单
	 */
	@Override
	public void createTablePlanOrder() {
		//查询出所有未暂停的餐桌计划
		//拼装订单
		//下单
	}

}
