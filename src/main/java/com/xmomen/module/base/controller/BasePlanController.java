package com.xmomen.module.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.base.mapper.BasePlanMapper;
import com.xmomen.module.base.model.CreatePlan;
import com.xmomen.module.base.model.ItemChildModel;
import com.xmomen.module.base.model.PlanModel;
import com.xmomen.module.base.model.UpdatePlan;
import com.xmomen.module.base.service.BasePlanService;
import com.xmomen.module.logger.Log;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class BasePlanController {

    @Autowired
    BasePlanService basePlanService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 计划列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/basePlan", method = RequestMethod.GET)
    @Log(actionName = "查询计划列表")
    public Page<PlanModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        return basePlanService.getPlanList(keyword, limit, offset);
    }

    /**
     *  计划列表
     * @param id
     */
    @RequestMapping(value = "/basePlan/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询计划")
    public CdPlan getUserList(@PathVariable(value = "id") Integer id){
        return basePlanService.getPlan(id);
    }

    /**
     * 新增计划
     * @param createPlan
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/basePlan", method = RequestMethod.POST)
    @Log(actionName = "新增计划")
    public CdPlan createPlan(@RequestBody @Valid CreatePlan createPlan, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return basePlanService.createPlan(createPlan);
    }

    /**
     * 更新计划
     * @param id
     * @param updatePlan
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/basePlan/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新计划")
    public CdPlan updatePlan(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdatePlan updatePlan, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
       return  basePlanService.updatePlan(id,updatePlan);
    }

    /**
     *  删除计划
     * @param id
     */
    @RequestMapping(value = "/basePlan/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除计划")
    public void deletePlan(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(CdPlan.class, id);
    }
    /**
     * 查询选择的产品
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/basePlan/getChosePlanItemList", method = RequestMethod.GET)
	public List<ItemChildModel> getChildItem(@RequestParam(value = "parentId", required = false) Integer parentId){
    	List<ItemChildModel> childItems = new ArrayList<ItemChildModel>();
    	Map map = new HashMap<String,Object>();
	    map.put("parentId", parentId);
	    childItems = mybatisDao.getSqlSessionTemplate().selectList(BasePlanMapper.BasePlanMapperNameSpace + "getChosePlanItemList", map);
		return childItems;
	}
}
