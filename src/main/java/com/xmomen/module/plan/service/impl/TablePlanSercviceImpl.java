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
		for(Integer planId : createTablePlan.getCdPlanIds()){
			TbTablePlan tablePlan = new TbTablePlan();
			tablePlan.setCdPlanId(planId);
			tablePlan.setAuditStatus(1);
			tablePlan.setCdMemberId(createTablePlan.getCdMemberId());
			tablePlan.setConsigneeAddress(createTablePlan.getConsigneeAddress());
			tablePlan.setConsigneeName(createTablePlan.getConsigneeName());
			tablePlan.setConsigneePhone(createTablePlan.getConsigneePhone());
			tablePlan.setCouponNumber(createTablePlan.getCouponNumber());
			tablePlan.setIsStop(0);
			tablePlan.setSendValue(0);
			CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,planId);
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

}
