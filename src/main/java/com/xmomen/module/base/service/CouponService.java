package com.xmomen.module.base.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import com.xmomen.module.base.entity.CdCouponRef;
import com.xmomen.module.base.entity.CdCouponRefExample;

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

    public void bindMember(String couponNumber, Integer memberId){

    }
    @Transactional
    public void sendOneCoupon(Integer id,Integer companyId,Integer customerMangerId,String couponNumber){
    	//更新卡发放状态
    	CdCoupon coupon = new CdCoupon();
    	coupon.setIsSend(1);
    	coupon.setId(id);
    	mybatisDao.updateByModel(coupon);
    	//先删除再添加
    	CdCouponRefExample couponRefExample = new CdCouponRefExample();
		couponRefExample.createCriteria().andCdCouponIdEqualTo(id)
		.andRefTypeEqualTo("SEND_COMPANY");
		mybatisDao.deleteByExample(couponRefExample);
    	CdCouponRef couponRef = new CdCouponRef();
    	couponRef.setCdCouponId(id);
    	couponRef.setRefType("SEND_COMPANY");
    	couponRef.setRefName("发放单位");
    	couponRef.setRefValue(companyId+"");
    	couponRef.setCouponNumber(couponNumber);
    	mybatisDao.save(couponRef);
    	//先删除再添加
    	CdCouponRefExample couponRefCustomerExample = new CdCouponRefExample();
    	couponRefCustomerExample.createCriteria().andCdCouponIdEqualTo(id)
		.andRefTypeEqualTo("SEND_CUSTOMER");
		mybatisDao.deleteByExample(couponRefCustomerExample);
    	CdCouponRef couponCustomerRef = new CdCouponRef();
    	couponCustomerRef.setCdCouponId(id);
    	couponCustomerRef.setRefType("SEND_CUSTOMER");
    	couponCustomerRef.setRefName("发放客户经理");
    	couponCustomerRef.setRefValue(customerMangerId+"");
    	couponCustomerRef.setCouponNumber(couponNumber);
    	mybatisDao.save(couponCustomerRef);
    }
}
