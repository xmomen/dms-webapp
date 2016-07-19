package com.xmomen.module.base.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdActivityAddress;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponRef;
import com.xmomen.module.base.entity.CdCouponRefExample;
import com.xmomen.module.base.mapper.CouponMapper;
import com.xmomen.module.base.model.CouponModel;
import com.xmomen.module.base.model.CouponQuery;
import com.xmomen.module.base.model.CreateCoupon;
import com.xmomen.module.base.model.ReadCardVo;
import com.xmomen.module.base.model.UpdateCoupon;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.logger.Log;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class CouponActivityController {

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
    @RequestMapping(value = "/couponActivity", method = RequestMethod.GET)
    @Log(actionName = "卡劵回填查询")
    public Page<CouponModel> getCouponList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "couponNumber", required = false) String couponNumber,
                                  @RequestParam(value = "couponType",required = false) Integer couponType,
                                  @RequestParam(value = "couponCategoryId",required = false)Integer couponCategoryId,
                                  @RequestParam(value = "isSend",required = false) Integer isSend,
                                  @RequestParam(value = "cdCompanyId",required = false) Integer cdCompanyId,
                                  @RequestParam(value = "customerMangerId",required = false) Integer customerMangerId,
                                  @RequestParam(value = "isUseful",required = false) Integer isUseful,
                                  @RequestParam(value = "isOver",required = false) Integer isOver,
                                  @RequestParam(value = "batch",required = false) String batch,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        CouponQuery couponQuery = new CouponQuery();
        couponQuery.setKeyword(keyword);
        couponQuery.setCdCompanyId(cdCompanyId);
        couponQuery.setCouponCategoryId(couponCategoryId);
        couponQuery.setCouponNumber(couponNumber);
        couponQuery.setCouponType(couponType);
        couponQuery.setCustomerMangerId(customerMangerId);
        couponQuery.setIsOver(isOver);
        couponQuery.setIsSend(isSend);
        couponQuery.setIsUseful(isUseful);
        if(!StringUtils.isBlank(batch)){
            couponQuery.setBatch(batch);
        }
        //客服经理过滤 如果有客服组权限则不过滤
        if(SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)){
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            couponQuery.setManagerId(userId);
        }
        return couponService.queryCouponActivity(couponQuery, limit, offset);
    }

}
