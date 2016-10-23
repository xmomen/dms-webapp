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
import com.xmomen.module.base.mapper.BasePlanMapper;
import com.xmomen.module.base.model.CreatePlan;
import com.xmomen.module.base.model.PlanItemModel;
import com.xmomen.module.base.model.PlanModel;
import com.xmomen.module.base.model.UpdatePlan;
import com.xmomen.module.base.service.BasePlanService;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.OrderModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasePlanServiceImpl implements BasePlanService {
	@Autowired
	MybatisDao mybatisDao;
	
	@SuppressWarnings("unchecked")
	@Override
	 public Page<PlanModel> getPlanList(String keyword, Integer limit, Integer offset){
		Map map = new HashMap();
		map.put("keyword" , keyword);
		return (Page<PlanModel>) mybatisDao.selectPage(BasePlanMapper.BasePlanMapperNameSpace + "getBasePlanList", map, limit, offset);
	 }
	
	@Override
	 public CdPlan getPlan(Integer id){
	        return mybatisDao.selectByPrimaryKey(CdPlan.class, id);
	 }
	
	@Override
	@Transactional
	public CdPlan createPlan(CreatePlan createPlan) {
		CdPlan plan = new CdPlan();
		plan.setCreateTime(mybatisDao.getGMTDate());
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		plan.setCreateUser(username);
		plan.setDeliverCount(createPlan.getDeliverCount());
		plan.setDeliveryTime(createPlan.getDeliveryTime());
		plan.setDeliveryType(createPlan.getDeliveryType());
		plan.setPlanName(createPlan.getPlanName());
		plan.setPrice(createPlan.getPrice());
		plan.setBeginTime(createPlan.getBeginTime());
		plan.setIsRandom(createPlan.getIsRandom());
		plan.setRandomNum(createPlan.getRandomNum());
		plan.setCdCategoryId(createPlan.getCdCategoryId());
		plan = mybatisDao.saveByModel(plan);
		if(plan.getIsRandom() == 0){
			for(PlanItemModel planItemModel : createPlan.getPlanItems()){
				CdPlanItem planItem = new CdPlanItem();
				planItem.setCdItemId(planItemModel.getCdItemId());
				planItem.setCdPlanId(plan.getId());
				planItem.setCountValue(planItemModel.getCount());
				mybatisDao.save(planItem);
			}
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
		plan.setPrice(updatePlan.getPrice());
		plan.setBeginTime(updatePlan.getBeginTime());
		plan.setIsRandom(updatePlan.getIsRandom());
		plan.setRandomNum(updatePlan.getRandomNum());
		plan.setCdCategoryId(updatePlan.getCdCategoryId());
		plan = mybatisDao.updateByModel(plan);
		CdPlanItemExample planItemExample = new CdPlanItemExample();
		planItemExample.createCriteria().andCdPlanIdEqualTo(id);
		mybatisDao.deleteByExample(planItemExample);
		if(plan.getIsRandom() == 0){
			for(PlanItemModel planItemModel : updatePlan.getPlanItems()){
				CdPlanItem planItem = new CdPlanItem();
				planItem.setCdItemId(planItemModel.getCdItemId());
				planItem.setCdPlanId(plan.getId());
				planItem.setCountValue(planItemModel.getCount());
				mybatisDao.save(planItem);
			}
		}
		return plan;
	}
	@Override
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdPlan.class, id);		
	}

}
