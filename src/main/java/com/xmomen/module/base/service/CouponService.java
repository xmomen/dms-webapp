package com.xmomen.module.base.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeng on 2016/3/30.
 */
@Service
public class CouponService {

    @Autowired
    MybatisDao mybatisDao;

    @Transactional
    public CdCoupon createCoupon(CdCoupon cdCoupon){
        return mybatisDao.saveByModel(cdCoupon);
    }

    @Transactional
    public void updateCoupon(CdCoupon cdCoupon){
        mybatisDao.update(cdCoupon);
    }

    public CdCoupon getCoupon(Integer id){
        return mybatisDao.selectByPrimaryKey(CdCoupon.class, id);
    }

    public Page<CdCoupon> getCouponList(String keyword, Integer limit, Integer offset){
        CdCouponExample cdCouponExample = new CdCouponExample();
        cdCouponExample.createCriteria().andCouponNumberLike("%" + StringUtils.trimToEmpty(keyword) + "%");
        return mybatisDao.selectPageByExample(cdCouponExample, limit, offset);
    }
}
