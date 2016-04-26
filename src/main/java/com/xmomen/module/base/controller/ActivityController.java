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
import com.xmomen.module.base.entity.CdActivity;
import com.xmomen.module.base.mapper.ActivityMapper;
import com.xmomen.module.base.mapper.CouponCategoryMapper;
import com.xmomen.module.base.model.ActivityModel;
import com.xmomen.module.base.model.CreateActivity;
import com.xmomen.module.base.model.ItemChildModel;
import com.xmomen.module.base.model.UpdateActivity;
import com.xmomen.module.base.service.ActivityService;
import com.xmomen.module.logger.Log;

/**
 */
@RestController
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 活动列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    @Log(actionName = "查询活动列表")
    public Page<ActivityModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
   	    Map map = new HashMap<String,Object>();
        map.put("keyword", keyword);
        return (Page<ActivityModel>) mybatisDao.selectPage(ActivityMapper.ActivityMapperNameSpace + "getActivityList", map, limit, offset);
    }


    /**
     * 新增活动
     * @param createActivity
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    @Log(actionName = "新增活动")
    public void createActivity(@RequestBody @Valid CreateActivity createActivity, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        activityService.createActivity(createActivity);
    }

    /**
     * 更新活动
     * @param id
     * @param updateActivity
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新活动")
    public void updateActivity(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateActivity updateActivity, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        activityService.updateActivity(id,updateActivity);
    }

    /**
     *  删除活动
     * @param id
     */
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除活动")
    public void deleteActivity(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(CdActivity.class, id);
    }
    /**
     * 查询选择的产品
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/activity/getChoseItemList", method = RequestMethod.GET)
	public List<ItemChildModel> getChildItem(@RequestParam(value = "parentId", required = false) Integer parentId){
    	List<ItemChildModel> childItems = new ArrayList<ItemChildModel>();
    	Map map = new HashMap<String,Object>();
	    map.put("parentId", parentId);
	    childItems = mybatisDao.getSqlSessionTemplate().selectList(ActivityMapper.ActivityMapperNameSpace + "getChoseItemList", map);
		return childItems;
	}
}
