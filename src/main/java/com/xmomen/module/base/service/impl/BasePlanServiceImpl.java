package com.xmomen.module.base.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.base.entity.CdManagerCompanyExample;
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.base.entity.CdPlanExample;
import com.xmomen.module.base.entity.CdPlanItem;
import com.xmomen.module.base.entity.CdPlanItemExample;
import com.xmomen.module.base.model.CreatePlan;
import com.xmomen.module.base.model.PlanItemModel;
import com.xmomen.module.base.model.UpdatePlan;
import com.xmomen.module.base.service.BasePlanService;
@Service
public class BasePlanServiceImpl implements BasePlanService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	 public Page<CdPlan> getPlanList(String keyword, Integer limit, Integer offset){
	        CdPlanExample cdPlanExample = new CdPlanExample();
	        cdPlanExample.createCriteria().andPlanNameLike("%" + StringUtils.trimToEmpty(keyword) + "%");
	        return mybatisDao.selectPageByExample(cdPlanExample, limit, offset);
	    }
	@Override
	 public CdPlan getPlan(Integer id){
	        return mybatisDao.selectByPrimaryKey(CdPlan.class, id);
	 }
	
	@Override
	@Transactional
	public CdPlan createPlan(CreatePlan createPlan) {
		CdPlan plan = new CdPlan();
		plan.setCreateTime(DateUtils.getCurrentGMTDate());
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		plan.setCreateUser(username);
		plan.setDeliverCount(createPlan.getDeliverCount());
		plan.setDeliveryTime(createPlan.getDeliveryTime());
		plan.setDeliveryType(createPlan.getDeliveryType());
		plan.setPlanName(createPlan.getPlanName());
		plan = mybatisDao.saveByModel(plan);
		for(PlanItemModel planItemModel : createPlan.getPlanItems()){
			CdPlanItem planItem = new CdPlanItem();
			planItem.setCdItemId(planItemModel.getCdItemId());
			planItem.setCdPlanId(plan.getId());
			planItem.setCountValue(planItemModel.getCount());
			mybatisDao.save(planItem);
		}
		return plan;
	}
	@Override
	@Transactional
	public CdPlan updatePlan(Integer id, UpdatePlan updatePlan) {
		CdPlan plan = new CdPlan();
		plan.setId(id);
		plan.setDeliverCount(updatePlan.getDeliverCount());
		plan.setDeliveryTime(updatePlan.getDeliveryTime());
		plan.setDeliveryType(updatePlan.getDeliveryType());
		plan.setPlanName(updatePlan.getPlanName());
		plan = mybatisDao.updateByModel(plan);
		CdPlanItemExample planItemExample = new CdPlanItemExample();
		planItemExample.createCriteria().andCdPlanIdEqualTo(id);
		mybatisDao.deleteByExample(planItemExample);
		for(PlanItemModel planItemModel : updatePlan.getPlanItems()){
			CdPlanItem planItem = new CdPlanItem();
			planItem.setCdItemId(planItemModel.getCdItemId());
			planItem.setCdPlanId(plan.getId());
			planItem.setCountValue(planItemModel.getCount());
			mybatisDao.save(planItem);
		}
		return plan;
	}
	@Override
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdPlan.class, id);		
	}

}
