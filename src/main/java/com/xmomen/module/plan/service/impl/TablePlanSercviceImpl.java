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
		TbTablePlan tablePlan = new TbTablePlan();
		tablePlan.setCdPlanId(createTablePlan.getCdPlanId());
		tablePlan.setAuditStatus(1);
		tablePlan.setCdMemberId(createTablePlan.getCdMemberId());
		tablePlan.setConsigneeAddress(createTablePlan.getConsigneeAddress());
		tablePlan.setConsigneeName(createTablePlan.getConsigneeName());
		tablePlan.setConsigneePhone(createTablePlan.getConsigneePhone());
		tablePlan.setIsStop(0);
		tablePlan.setMemberCode(createTablePlan.getMemberCode());
		tablePlan.setSendValue(0);
		CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,createTablePlan.getCdPlanId());
		tablePlan.setTotalSendValue(plan.getDeliverCount());
		mybatisDao.saveByModel(tablePlan);
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
		tablePlan.setMemberCode(updateTablePlan.getMemberCode());
		CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,updateTablePlan.getCdPlanId());
		tablePlan.setTotalSendValue(plan.getDeliverCount());
		mybatisDao.saveByModel(tablePlan);
	}
	
	@Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(TbTablePlan.class, id);
    }

}
