package com.xmomen.module.job.controller;

import java.sql.Date;
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
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.job.mapper.PackageTaskMapper;
import com.xmomen.module.job.model.CreatePackageTask;
import com.xmomen.module.job.model.PackageTaskModel;
import com.xmomen.module.job.service.PackageTaskService;
import com.xmomen.module.logger.Log;

/**
 */
@RestController
public class PackageTaskController {
	
	@Autowired
	PackageTaskService packageTaskService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 包装任务
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/packageTask", method = RequestMethod.GET)
    @Log(actionName = "查询包装任务")
    public Page<PackageTaskModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "viewType", required = false) String viewType,
                                  @RequestParam(value = "packageTaskId", required = false) Integer packageTaskId,
                                  @RequestParam(value = "nextPackageTaskId", required = false) Integer nextPackageTaskId,
                                  @RequestParam(value = "packageTaskCreateTimeStart",required = false) String packageTaskCreateTimeStart,
                                  @RequestParam(value = "packageTaskCreateTimeEnd",required = false) String packageTaskCreateTimeEnd){
    	 Map param = new HashMap();
         param.put("keyword", keyword);
         param.put("packageTaskId", packageTaskId);
         param.put("viewType",viewType);
         if(StringUtilsExt.isNotBlank(packageTaskCreateTimeStart)){
        	 param.put("packageTaskCreateTimeStart",packageTaskCreateTimeStart.substring(0, 10));
         }
         if(StringUtilsExt.isNotBlank(packageTaskCreateTimeEnd)){
        	 param.put("packageTaskCreateTimeEnd",packageTaskCreateTimeEnd.substring(0, 10));
         }
         if(SecurityUtils.getSubject().hasRole(AppConstants.PACKAGE_PERMISSION_CODE)){
             Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
             param.put("userId", userId);
         }
         param.put("nextPackageTaskId",nextPackageTaskId);
         Page<PackageTaskModel> packageTasks = (Page<PackageTaskModel>) mybatisDao.selectPage(PackageTaskMapper.PackageTaskMapperNameSpace + "getPackageTaskList", param, limit, offset);
         if(packageTasks.getResult().size() == 0 && nextPackageTaskId !=null){
        	 param.put("nextPackageTaskId",null);
        	 param.put("nextPackageTaskIdZero",1);
             packageTasks = (Page<PackageTaskModel>) mybatisDao.selectPage(PackageTaskMapper.PackageTaskMapperNameSpace + "getPackageTaskList", param, limit, offset);
         }
        return  packageTasks;
    }

    /**
     * 新增包装任务
     * @param createPurchase
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/packageTask", method = RequestMethod.POST)
    @Log(actionName = "新增包装任务")
    public void packageTask(@RequestBody @Valid CreatePackageTask createPackageTask, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        packageTaskService.createPackageTask(createPackageTask);
    }

    /**
     * 开始包装
     */
    /**
     * 新增包装任务
     * @param createPurchase
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/packageTask/{id}/packageWorking",method = RequestMethod.PUT)
    @Log(actionName = "开始包装")
    public void packageWorking(
    		@PathVariable(value = "id") Integer id,
            @RequestParam(value = "barCode") String barCode
    		){
        packageTaskService.packageWorking(id,barCode);
    }
}
