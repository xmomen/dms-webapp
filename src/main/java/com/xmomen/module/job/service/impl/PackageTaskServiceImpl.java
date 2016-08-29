package com.xmomen.module.job.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.model.UpdateItem;
import com.xmomen.module.job.entity.TbJob;
import com.xmomen.module.job.entity.TbJobOperationLog;
import com.xmomen.module.job.model.CreatePackageTask;
import com.xmomen.module.job.service.PackageTaskService;
import com.xmomen.module.order.entity.TbPurchase;

@Service
public class PackageTaskServiceImpl implements PackageTaskService {

    @Autowired
    MybatisDao mybatisDao;
	@Override
	public void createPackageTask(CreatePackageTask createPackageTask) {
		 Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);//登陆用户
		 TbJob packageTask = new TbJob();
		 packageTask.setCdItemId(createPackageTask.getCdItemId());
		 packageTask.setCountValue(createPackageTask.getCountValue());
		 packageTask.setJobCreateUser(userId);
		 packageTask.setJobType(1);
		 packageTask.setJobStatus(0);
		 packageTask.setJobCreateTime(mybatisDao.getSysdate());
		 packageTask.setJobUser(createPackageTask.getJobUser());
		 packageTask.setPurchaseCode(createPackageTask.getPurchaseCode());
		 packageTask.setPurchaseId(createPackageTask.getPurchaseId());
		 mybatisDao.save(packageTask);
		 //更新采购单的明细分配数量
		 TbPurchase purchase = mybatisDao.selectByPrimaryKey(TbPurchase.class, createPackageTask.getPurchaseId());
		 int distributeValue = purchase.getDistributeValue();
		 distributeValue = distributeValue + createPackageTask.getCountValue();
		 purchase.setDistributeValue(distributeValue);
		 mybatisDao.update(purchase);
	}

	@Override
	public void packageWorking(Integer id, String barCodes) {
		String[] barCodeList = barCodes.split(",");
		TbJob packageTask = mybatisDao.selectByPrimaryKey(TbJob.class, id);
		for(String barCode : barCodeList){
			Date currentTime = mybatisDao.getSysdate();
			Integer finishValue = packageTask.getFinishValue();
			Integer countValue = packageTask.getCountValue();
			Integer currentFinishValue = finishValue + 1;
			if(packageTask.getJobBeginTime() == null){
				packageTask.setJobBeginTime(currentTime);
				packageTask.setJobStatus(1);
				
			}
			if(countValue.intValue() == currentFinishValue.intValue()){
				packageTask.setFinishTime(currentTime);
				packageTask.setJobStatus(2);
			}
			packageTask.setFinishValue(currentFinishValue);
			mybatisDao.update(packageTask);
			//添加操作记录
			TbJobOperationLog jobOperationLog = new TbJobOperationLog();
			jobOperationLog.setBarCode(barCode);
			jobOperationLog.setItemCode(StringUtils.substring(barCode,0,7));
			jobOperationLog.setJobId(packageTask.getId());
			jobOperationLog.setJobTime(currentTime);
			jobOperationLog.setJobUser(packageTask.getJobUser());
			mybatisDao.save(jobOperationLog);
			//更新包装数
		}
	}

	@Override
	public void updatePackageTask(Integer id, UpdateItem updatePackageTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
