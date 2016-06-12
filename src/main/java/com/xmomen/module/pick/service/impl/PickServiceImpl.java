package com.xmomen.module.pick.service.impl;

import java.math.BigDecimal;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.pick.entity.TbPick;
import com.xmomen.module.pick.model.PickVo;
import com.xmomen.module.pick.service.PickService;
import com.xmomen.module.system.entity.SysUserOrganization;
@Service
public class PickServiceImpl implements PickService {

	@Autowired
	private MybatisDao mybatisDao;
	@Override
	@Transactional
	public void pick(PickVo pickVo) {
		CdCoupon coupon = new CdCoupon();
		coupon.setCouponNumber(pickVo.getCouponNo());
		coupon = mybatisDao.selectOneByModel(coupon);
		AssertExt.notNull(coupon,"卡号不存在！");
		int pickPayType = pickVo.getPickPayType().intValue();
		BigDecimal pickPrice = pickVo.getPickPrice();
		BigDecimal casePrice = pickVo.getPickCasePrice() == null ? BigDecimal.ZERO : pickVo.getPickCasePrice();
		BigDecimal cradPrice = pickPrice.subtract(casePrice);
		if(pickPayType == 1 || pickPayType == 2){//刷卡 || 现金+刷卡
			BigDecimal usePrice = coupon.getUserPrice();
			BigDecimal diffPrice = usePrice.subtract(cradPrice);
			AssertExt.isTrue(diffPrice.doubleValue() >= 0.00,"余额不足");
			coupon.setUserPrice(diffPrice);
			mybatisDao.update(coupon);
		}else if(pickPayType == 3){//现金
			AssertExt.isTrue(casePrice.compareTo(pickPrice) != 0,"付款金额错误");
		}
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		SysUserOrganization userOrganization = new SysUserOrganization();
		userOrganization.setUserId(userId);
		userOrganization = mybatisDao.selectOneByModel(userOrganization);
		TbPick pick = new TbPick();
		pick.setCouponNo(pickVo.getCouponNo());
		pick.setPickCasePrice(casePrice);
		pick.setPickCradPrice(cradPrice);
		pick.setPickDate(mybatisDao.getSysdate());
		pick.setPickPlaceUser(userId);
		pick.setPickPlace(userOrganization.getOrganizationId());
		pick.setPickTotalPrice(pickPrice);
		pick.setPickWeight(pickVo.getPickWeight());
		mybatisDao.save(pick);
	}

}
