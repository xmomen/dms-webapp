package com.xmomen.module.base.service;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import com.xmomen.module.base.entity.CdCouponRef;
import com.xmomen.module.base.entity.CdCouponRefExample;
import com.xmomen.module.base.mapper.CouponMapper;
import com.xmomen.module.base.model.CouponModel;
import com.xmomen.module.base.model.CouponQuery;
import com.xmomen.module.pick.entity.TbRechargeLog;
import com.xmomen.module.system.entity.SysUserOrganization;

/**
 * Created by Jeng on 2016/3/30.
 */
@Service
public class CouponService {

    @Autowired
    MybatisDao mybatisDao;

	@Autowired
	ItemService itemService;

	/**
	 * 查询卡券信息
	 * @param couponQuery
	 * @param limit
	 * @param offset
	 * @return
	 */
	public Page<CouponModel> queryCoupon(CouponQuery couponQuery, Integer limit, Integer offset){
		return (Page<CouponModel>) mybatisDao.selectPage(CouponMapper.CouponMapperNameSpace + "getCouponList", couponQuery, limit, offset);
	}

	public CouponModel queryOneCoupon(CouponQuery couponQuery){
		return mybatisDao.getSqlSessionTemplate().selectOne(CouponMapper.CouponMapperNameSpace + "getCouponList", couponQuery);
	}

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
    public void sendOneCoupon(Integer id,Integer companyId,Integer customerMangerId,String couponNumber,String batch){
    	//更新卡发放状态
    	CdCoupon coupon = new CdCoupon();
    	coupon.setIsSend(1);
    	coupon.setId(id);
    	coupon.setBatch(batch);
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
    
    @Transactional
    public void returnCoupon(Integer id){
    	//先删除再添加
    	CdCouponRefExample couponRefExample = new CdCouponRefExample();
		couponRefExample.createCriteria().andCdCouponIdEqualTo(id)
		.andRefTypeEqualTo("SEND_COMPANY");
		mybatisDao.deleteByExample(couponRefExample);	
		CdCouponRefExample couponRefCustomerExample = new CdCouponRefExample();
    	couponRefCustomerExample.createCriteria().andCdCouponIdEqualTo(id)
		.andRefTypeEqualTo("SEND_CUSTOMER");
		mybatisDao.deleteByExample(couponRefCustomerExample);
		//更新卡券为未发送
    	CdCoupon coupon = new CdCoupon();
    	coupon.setIsSend(0);
    	coupon.setId(id);
    	mybatisDao.updateByModel(coupon);
    }
    
    @Transactional
    public void cardRecharge(String couponNo,BigDecimal rechargePrice){
    	CdCoupon coupon = new CdCoupon();
		coupon.setCouponNumber(couponNo);
		coupon = mybatisDao.selectOneByModel(coupon);
		AssertExt.notNull(coupon,"卡号不存在！");
		coupon.setUserPrice(coupon.getUserPrice().add(rechargePrice));
		mybatisDao.update(coupon);
		
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		SysUserOrganization userOrganization = new SysUserOrganization();
		userOrganization.setUserId(userId);
		userOrganization = mybatisDao.selectOneByModel(userOrganization);
		TbRechargeLog rechargeLog = new TbRechargeLog();
		rechargeLog.setCouponNo(couponNo);
		rechargeLog.setRechargeDate(mybatisDao.getSysdate());
		rechargeLog.setRechargePlace(userOrganization.getOrganizationId());
		rechargeLog.setRechargePrice(rechargePrice);
		rechargeLog.setRechargeUser(userId);
		mybatisDao.save(rechargeLog);
    }
}
