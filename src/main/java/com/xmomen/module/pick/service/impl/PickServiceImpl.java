package com.xmomen.module.pick.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.entity.CdMemberCouponRelation;
import com.xmomen.module.pick.entity.TbPick;
import com.xmomen.module.pick.entity.TbRechargeLog;
import com.xmomen.module.pick.model.CreateMember;
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
	@Override
	public void pickCard(CreateMember createMember) {
		CdCoupon coupon = new CdCoupon();
		coupon.setCouponNumber(createMember.getNewCouponNo());
		coupon.setCouponPassword(createMember.getNewPassword());
		coupon = mybatisDao.selectOneByModel(coupon);
		AssertExt.notNull(coupon,"卡号不存在！");
		
		CdMemberCouponRelation cdMemberCouponRelation = new CdMemberCouponRelation();
		cdMemberCouponRelation.setCouponNumber(coupon.getCouponNumber());
		cdMemberCouponRelation = mybatisDao.selectOneByModel(cdMemberCouponRelation);
		AssertExt.isNull(cdMemberCouponRelation,"该卡已绑定客户，不能再绑定");
		
		
		CdMember member = new CdMember();
		member.setMemberType(createMember.getMemberType());
		member.setName(createMember.getName());
		member.setPhoneNumber(createMember.getPhoneNumber());
		member.setSpareName(createMember.getSpareName());
		member.setSpareName2(createMember.getSpareName2());
		member.setSpareTel(createMember.getSpareTel());
		member.setSpareTel2(createMember.getSpareTel2());
		member.setTelNumber(createMember.getTelNumber());
		member.setOfficeTel(createMember.getOfficeTel());
		member.setAddress(createMember.getAddress());
		member.setSpareAddress(createMember.getSpareAddress());
		member.setSpareAddress2(createMember.getSpareAddress2());
		member.setCdCompanyId(createMember.getCdCompanyId());
		member.setCdUserId(createMember.getCdUserId());
		member = mybatisDao.insertByModel(member);
		CdMemberCouponRelation cdMemberCouponRelation2 = new CdMemberCouponRelation();
		cdMemberCouponRelation2.setCdMemberId(member.getId());
		cdMemberCouponRelation2.setCouponNumber(coupon.getCouponNumber());
		mybatisDao.insert(cdMemberCouponRelation2);
		//记录充值记录
		BigDecimal userPrice = coupon.getUserPrice()==null?BigDecimal.ZERO:coupon.getUserPrice();
		coupon.setUserPrice(userPrice.add(createMember.getUserPrice()));
		mybatisDao.update(coupon);
		
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
		SysUserOrganization userOrganization = new SysUserOrganization();
		userOrganization.setUserId(userId);
		userOrganization = mybatisDao.selectOneByModel(userOrganization);
		TbRechargeLog rechargeLog = new TbRechargeLog();
		rechargeLog.setCouponNo(coupon.getCouponNumber());
		rechargeLog.setRechargeDate(mybatisDao.getSysdate());
		rechargeLog.setRechargePlace(userOrganization.getOrganizationId());
		rechargeLog.setRechargePrice(createMember.getUserPrice());
		rechargeLog.setRechargeUser(userId);
		mybatisDao.save(rechargeLog);
	}

}
