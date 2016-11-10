package com.xmomen.module.job.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.job.mapper.JobOperationLogMapper;
import com.xmomen.module.job.model.CreateJobOperationLog;
import com.xmomen.module.job.model.JobOperationLogModel;
import com.xmomen.module.job.service.JobOperationLogService;
import com.xmomen.module.logger.Log;

/**
 */
@RestController
public class JobOperationLogController {
	
	@Autowired
	JobOperationLogService jobOperationLogService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 查看包装操作记录
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/jobOperationLog", method = RequestMethod.GET)
    @Log(actionName = "查看包装操作记录")
    public Page<JobOperationLogModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "jobId", required = false) Integer jobId,
                                  @RequestParam(value = "upc", required = false) String upc){
    	 Map param = new HashMap();
         param.put("keyword", keyword);
         param.put("jobId", jobId);
         param.put("upc",upc);
        return  (Page<JobOperationLogModel>) mybatisDao.selectPage(JobOperationLogMapper.JobOperationLogMapperNameSpace + "getJobOperationLogList", param, limit, offset);
    }

    /**
     *  删除包装操作记录
     * @param id
     */
    @RequestMapping(value = "/jobOperationLog/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "包装操作记录")
    public void deletePlan(@PathVariable(value = "id") Integer id){
        this.jobOperationLogService.delete(id);
    }
}
