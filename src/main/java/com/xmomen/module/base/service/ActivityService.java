package com.xmomen.module.base.service;

import com.xmomen.module.base.model.CreateActivity;
import com.xmomen.module.base.model.UpdateActivity;


public interface ActivityService {
	public void createActivity(CreateActivity createActivity);
	
	public void updateActivity(Integer id,UpdateActivity updateActivity);
	
	public void delete(Integer id);
}	
