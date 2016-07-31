package com.xmomen.module.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdActivity;
import com.xmomen.module.base.entity.CdActivityRef;
import com.xmomen.module.base.entity.CdActivityRefExample;
import com.xmomen.module.base.model.ActivityRefModel;
import com.xmomen.module.base.model.CreateActivity;
import com.xmomen.module.base.model.UpdateActivity;
import com.xmomen.module.base.service.ActivityService;
@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createActivity(CreateActivity createActivity) {
		CdActivity activity = new CdActivity();
		activity.setActivityName(createActivity.getActivityName());
		activity.setActivityType(createActivity.getActivityType());
		activity.setActivityBeginTime(createActivity.getActivityBeginTime());
		activity.setActivityEndTime(createActivity.getActivityEndTime());
		activity.setLowestPrice(createActivity.getLowestPrice());
		activity.setActivityDescribe(createActivity.getActivityDescribe());
		activity.setAvailable(createActivity.getAvailable());
		activity = mybatisDao.saveByModel(activity);
		if(1 == createActivity.getActivityType()){//固定卡
			CdActivityRef cdActivityRef = new CdActivityRef();
			cdActivityRef.setCdActivityId(activity.getId());
			cdActivityRef.setRefType("COUPON");
			cdActivityRef.setRefValue(createActivity.getCouponCategory()+"");
			cdActivityRef.setRefName("按卡类型");
			mybatisDao.save(cdActivityRef);
		}
		if(createActivity.getActivityRefList() != null){
			for(ActivityRefModel activityRef : createActivity.getActivityRefList()){
				CdActivityRef cdActivityRef = new CdActivityRef();
				cdActivityRef.setCdActivityId(activity.getId());
				cdActivityRef.setRefType("GIFT_ITEM");
				cdActivityRef.setRefValue(activityRef.getCdItemId()+"");
				cdActivityRef.setRefName("赠送产品");
				cdActivityRef.setRefCount(activityRef.getCount());
				mybatisDao.save(cdActivityRef);
			}
		}
	}
	@Transactional
	public void updateActivity(Integer id, UpdateActivity updateActivity) {
		CdActivity activity = new CdActivity();
		activity.setId(id);
		activity.setActivityName(updateActivity.getActivityName());
		activity.setActivityType(updateActivity.getActivityType());
		activity.setActivityBeginTime(updateActivity.getActivityBeginTime());
		activity.setActivityEndTime(updateActivity.getActivityEndTime());
		activity.setActivityDescribe(updateActivity.getActivityDescribe());
		activity.setAvailable(updateActivity.getAvailable());
		activity.setLowestPrice(updateActivity.getLowestPrice());
		activity = mybatisDao.saveByModel(activity);
		//先删除再添加
		CdActivityRefExample activityRefExample = new CdActivityRefExample();
		activityRefExample.createCriteria().andCdActivityIdEqualTo(id)
		.andRefTypeEqualTo("COUPON");
		mybatisDao.deleteByExample(activityRefExample);
		if(1 == updateActivity.getActivityType()){//固定卡类型
			CdActivityRef cdActivityRef = new CdActivityRef();
			cdActivityRef.setCdActivityId(activity.getId());
			cdActivityRef.setRefType("COUPON");
			cdActivityRef.setRefValue(updateActivity.getCouponCategory()+"");
			cdActivityRef.setRefName("按卡类型");
			mybatisDao.save(cdActivityRef);
		}
		if(updateActivity.getActivityRefList() != null){
			//先删除再添加
			CdActivityRefExample activityRefItemExample = new CdActivityRefExample();
			activityRefItemExample.createCriteria().andCdActivityIdEqualTo(id)
			.andRefTypeEqualTo("GIFT_ITEM");
			mybatisDao.deleteByExample(activityRefItemExample);
			for(ActivityRefModel activityRef : updateActivity.getActivityRefList()){
				CdActivityRef cdActivityRef = new CdActivityRef();
				cdActivityRef.setCdActivityId(activity.getId());
				cdActivityRef.setRefType("GIFT_ITEM");
				cdActivityRef.setRefValue(activityRef.getCdItemId()+"");
				cdActivityRef.setRefName("赠送产品");
				cdActivityRef.setRefCount(activityRef.getCount());
				mybatisDao.save(cdActivityRef);
			}
		}
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdActivity.class, id);		
	}

}
