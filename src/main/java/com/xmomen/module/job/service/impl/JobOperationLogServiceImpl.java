package com.xmomen.module.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.job.entity.TbJob;
import com.xmomen.module.job.entity.TbJobOperationLog;
import com.xmomen.module.job.service.JobOperationLogService;

@Service
public class JobOperationLogServiceImpl implements JobOperationLogService {

    @Autowired
    MybatisDao mybatisDao;

    @Override
	public void delete(Integer id) {
    	TbJobOperationLog jobOperationLog = mybatisDao.selectByPrimaryKey(TbJobOperationLog.class, id);
    	TbJob job = mybatisDao.selectByPrimaryKey(TbJob.class, jobOperationLog.getJobId());
    	mybatisDao.delete(jobOperationLog);
    	job.setFinishValue(job.getFinishValue() - 1);
    	//改变任务状态
    	if(job.getFinishValue() == 0){
    		job.setJobStatus(0);
    	}
    	if(job.getJobStatus() == 2){
    		job.setJobStatus(1);
    	}
    	mybatisDao.save(job);
    }

}
