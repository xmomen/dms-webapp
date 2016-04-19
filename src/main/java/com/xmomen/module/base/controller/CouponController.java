package com.xmomen.module.base.controller;

import java.util.HashMap;
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
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.mapper.CouponMapper;
import com.xmomen.module.base.model.CouponModel;
import com.xmomen.module.base.model.CreateCoupon;
import com.xmomen.module.base.model.UpdateCoupon;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.logger.Log;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 卡券列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/coupon", method = RequestMethod.GET)
    @Log(actionName = "查询卡券列表")
    public Page<CouponModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "couponNumber", required = false) String couponNumber,
                                  @RequestParam(value = "keyword", required = false) String keyword){
   	    Map map = new HashMap<String,Object>();
        map.put("keyword", keyword);
        map.put("couponNumber", couponNumber);
        return (Page<CouponModel>) mybatisDao.selectPage(CouponMapper.CouponMapperNameSpace + "getCouponList", map, limit, offset);
    }

    /**
     *  卡券列表
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询卡券")
    public CdCoupon getUserList(@PathVariable(value = "id") Integer id){
        return couponService.getCoupon(id);
    }

    /**
     * 新增卡券
     * @param createCoupon
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    @Log(actionName = "新增卡券")
    public CdCoupon createCoupon(@RequestBody @Valid CreateCoupon createCoupon, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CdCoupon cdCoupon = new CdCoupon();
        cdCoupon.setCouponType(createCoupon.getCouponType());
        cdCoupon.setCouponCategory(createCoupon.getCouponCategory());
        cdCoupon.setCouponDesc(createCoupon.getCouponDesc());
        cdCoupon.setCouponNumber(createCoupon.getCouponNumber());
        cdCoupon.setCouponPassword(createCoupon.getCouponPassword());
        cdCoupon.setBeginTime(createCoupon.getBeginTime());
        cdCoupon.setEndTime(createCoupon.getEndTime());
        cdCoupon.setCouponValue(createCoupon.getCouponValue());
        cdCoupon.setIsGift(createCoupon.getIsGift());
        cdCoupon.setIsUsed(createCoupon.getIsUsed());
        cdCoupon.setIsUseful(createCoupon.getIsUseful());
        cdCoupon.setNotes(createCoupon.getNotes());
        return couponService.createCoupon(cdCoupon);
    }

    /**
     * 更新卡券
     * @param id
     * @param updateCoupon
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新卡券")
    public void updateCoupon(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateCoupon updateCoupon, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CdCoupon cdCoupon = new CdCoupon();
        cdCoupon.setId(id);
        cdCoupon.setCouponCategory(updateCoupon.getCouponCategory());
        cdCoupon.setCouponType(updateCoupon.getCouponType());
        cdCoupon.setCouponDesc(updateCoupon.getCouponDesc());
        cdCoupon.setCouponNumber(updateCoupon.getCouponNumber());
        cdCoupon.setCouponPassword(updateCoupon.getCouponPassword());
        cdCoupon.setBeginTime(updateCoupon.getBeginTime());
        cdCoupon.setEndTime(updateCoupon.getEndTime());
        cdCoupon.setCouponValue(updateCoupon.getCouponValue());
        cdCoupon.setIsGift(updateCoupon.getIsGift());
        cdCoupon.setIsUsed(updateCoupon.getIsUsed());
        cdCoupon.setIsUseful(updateCoupon.getIsUseful());
        cdCoupon.setNotes(updateCoupon.getNotes());
        couponService.updateCoupon(cdCoupon);
    }

    /**
     *  删除卡券
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除卡券")
    public void deleteCoupon(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(CdCoupon.class, id);
    }

}
