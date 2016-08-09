package com.xmomen.module.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.job.entity.TbJob;
import com.xmomen.module.job.entity.TbJobOperationLog;
import com.xmomen.module.job.mapper.JobOperationLogMapper;
import com.xmomen.module.job.mapper.PackageTaskMapper;
import com.xmomen.module.job.model.JobOperationLogModel;
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
    	mybatisDao.save(job);
    	//改变任务状态
    	if(job.getFinishValue() == 0){
    		mybatisDao.getSqlSessionTemplate().update(PackageTaskMapper.PackageTaskMapperNameSpace + "updateBeginTime",jobOperationLog.getJobId());
    	}
    	if(job.getJobStatus() == 2){
    		mybatisDao.getSqlSessionTemplate().update(PackageTaskMapper.PackageTaskMapperNameSpace + "updateFinishTime",jobOperationLog.getJobId());
    	}
    }

}
