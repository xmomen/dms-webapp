package com.xmomen.module.plan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import com.xmomen.module.logger.Log;
import com.xmomen.module.plan.entity.TbTablePlan;
import com.xmomen.module.plan.mapper.TablePlanMapper;
import com.xmomen.module.plan.model.CreateTablePlan;
import com.xmomen.module.plan.model.TablePlanModel;
import com.xmomen.module.plan.model.UpdateTablePlan;
import com.xmomen.module.plan.service.TablePlanSercvice;
import com.xmomen.module.user.entity.SysUsers;

/**
 * Created by ted on 16/3/28.
 */
@RestController
public class TablePlanController {

    @Autowired
    TablePlanSercvice tablePlanService;
    @Autowired
    TablePlanMapper tablePlanMapper;
    @Autowired
    MybatisDao mybatisDao;
    /**
     * 查询餐桌信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/tablePlan", method = RequestMethod.GET)
    @Log(actionName = "查询餐桌信息")
    public Page<TablePlanModel> getTablePlanList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
         if(StringUtils.trimToNull(phoneNumber) != null){
             map.put("phoneNumber", phoneNumber);
         }
         //客服经理过滤 如果有客服组权限则不过滤
         if(SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)){
             Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
             map.put("userId",userId);
         }
        return (Page<TablePlanModel>) mybatisDao.selectPage(TablePlanMapper.TablePlanMapperNameSpace + "getTablePlanList", map, limit, offset);
    }
    
    
    @RequestMapping(value = "/tablePlan", method = RequestMethod.POST)
    @Log(actionName = "新增餐桌")
    public void createTablePlan(@RequestBody @Valid CreateTablePlan createTablePlan, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        tablePlanService.createTablePlan(createTablePlan);
    }

    /**
     *  根据ID查询餐桌信息
     * @param id
     */
    @RequestMapping(value = "/tablePlan/{id}", method = RequestMethod.GET)
    @Log(actionName = "根据ID查询餐桌信息")
    public TablePlanModel getTablePlan(@PathVariable(value = "id") Integer id){
        Map map = new HashMap<String,Object>();
        map.put("id", id);
        return mybatisDao.getSqlSessionTemplate().selectOne(TablePlanMapper.TablePlanMapperNameSpace + "getTablePlanList", map);
    }

    /**
     *  修改
     * @param id
     */
    @RequestMapping(value = "/tablePlan/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改餐桌信息")
    public void updateTablePlan(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid UpdateTablePlan updateTablePlan, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        tablePlanService.updateTablePlan(id,updateTablePlan);
    }
    
    /**
     *  删除
     * @param id
     */
    @RequestMapping(value = "/tablePlan/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除餐桌信息")
    public void deleteTablePlan(@PathVariable(value = "id") Integer id){
    	tablePlanService.delete(id);
    }
    
    /**
     *  暂停配送
     * @param id
     */
    @RequestMapping(value = "/tablePlan/{id}/stop", method = RequestMethod.PUT)
    @Log(actionName = "暂停配送")
    public void stop(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "locked") Boolean locked){
        TbTablePlan tablePlan = new TbTablePlan();
        tablePlan.setIsStop(locked ? 1 : 0);
        tablePlan.setId(id);
        mybatisDao.update(tablePlan);
    }
    
    /**
     *  暂停配送
     * @param id
     */
    @RequestMapping(value = "/tablePlan/createPlanOrder", method = RequestMethod.PUT)
    @Log(actionName = "手工调用")
    public void createPlanOrder(){
    	tablePlanService.createTablePlanOrder();
    }
}
