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
import com.xmomen.module.base.entity.CdCouponCategory;
import com.xmomen.module.base.mapper.CouponCategoryMapper;
import com.xmomen.module.base.model.CreateCouponCategory;
import com.xmomen.module.base.model.ItemChildModel;
import com.xmomen.module.base.model.UpdateCouponCategory;
import com.xmomen.module.base.service.CouponCategoryService;
import com.xmomen.module.logger.Log;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class CouponCategoryController {

    @Autowired
    CouponCategoryService couponCategoryService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 卡券类别列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/couponCategory", method = RequestMethod.GET)
    @Log(actionName = "查询卡券类别列表")
    public Page<CdCouponCategory> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "categoryType", required = false) Integer categoryType){
        return couponCategoryService.getCouponCategoryList(keyword,categoryType, limit, offset);
    }

    /**
     *  卡券类别列表
     * @param id
     */
    @RequestMapping(value = "/couponCategory/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询卡券类别")
    public CdCouponCategory getUserList(@PathVariable(value = "id") Integer id){
        return couponCategoryService.getCouponCategory(id);
    }

    /**
     * 新增卡券类别
     * @param createCouponCategory
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/couponCategory", method = RequestMethod.POST)
    @Log(actionName = "新增卡券类别")
    public CdCouponCategory createCouponCategory(@RequestBody @Valid CreateCouponCategory createCouponCategory, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return couponCategoryService.createCouponCategory(createCouponCategory);
    }

    /**
     * 更新卡券类别
     * @param id
     * @param updateCouponCategory
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/couponCategory/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新卡券类别")
    public CdCouponCategory updateCouponCategory(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateCouponCategory updateCouponCategory, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
       return  couponCategoryService.updateCouponCategory(id,updateCouponCategory);
    }

    /**
     *  删除卡券类别类别
     * @param id
     */
    @RequestMapping(value = "/couponCategory/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除卡券类别")
    public void deleteCouponCategory(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(CdCouponCategory.class, id);
    }
    /**
     * 查询选择的产品
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/getChoseItemList", method = RequestMethod.GET)
	public List<ItemChildModel> getChildItem(@RequestParam(value = "parentId", required = false) Integer parentId){
    	List<ItemChildModel> childItems = new ArrayList<ItemChildModel>();
    	Map map = new HashMap<String,Object>();
	    map.put("parentId", parentId);
	    childItems = mybatisDao.getSqlSessionTemplate().selectList(CouponCategoryMapper.CouponCategoryMapperNameSpace + "getChoseItemList", map);
		return childItems;
	}
}
