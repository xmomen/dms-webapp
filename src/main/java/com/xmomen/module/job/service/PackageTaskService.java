package com.xmomen.module.job.service;

import com.xmomen.module.base.model.UpdateItem;
import com.xmomen.module.job.model.CreatePackageTask;

public interface PackageTaskService {


	public void createPackageTask(CreatePackageTask createPackageTask);
	
	public void updatePackageTask(Integer id,UpdateItem updatePackageTask);
	
	public void delete(Integer id);
	
	public void packageWorking(Integer id ,String barCode);
}
