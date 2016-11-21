package com.xmomen.module.base.service.impl;

import java.util.List;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdActivityAddress;
import com.xmomen.module.base.entity.CdMemberCouponRelation;
import com.xmomen.module.base.entity.CdMemberCouponRelationExample;
import com.xmomen.module.base.service.CouponService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CouponActivityAddress;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.model.UpdateMember;
import com.xmomen.module.base.service.MemberSercvice;
import com.xmomen.module.order.entity.TbOrderItemExample;

@Service
public class MemberSercviceImpl implements MemberSercvice {
	@Autowired
	MybatisDao mybatisDao;

	@Autowired
	CouponService couponService;

	@Override
	@Transactional
	public void createMember(CreateMember createMember) {

		CdMember member = new CdMember();
		member.setPhoneNumber(createMember.getPhoneNumber());
		member = mybatisDao.selectOneByModel(member);
		if(member == null){
			member = new CdMember();
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
		}
		if(!StringUtils.isBlank(createMember.getCouponNumber())){
			CdMemberCouponRelation cdMemberCouponRelation = new CdMemberCouponRelation();
			cdMemberCouponRelation.setCdMemberId(member.getId());
			cdMemberCouponRelation.setCouponNumber(createMember.getCouponNumber());
			mybatisDao.insert(cdMemberCouponRelation);
			//查看卡是否有送礼品地址 如果有填充到第3个地址里面
			CdActivityAddress couponActivityAddress = new CdActivityAddress();
			couponActivityAddress.setCouponNumber(createMember.getCouponNumber());
			List<CdActivityAddress> couponAddressList =  mybatisDao.selectByModel(couponActivityAddress);
			if(couponAddressList.size() > 0){
				couponActivityAddress = couponAddressList.get(0);
				member.setSpareAddress2(couponActivityAddress.getConsignmentAddress());
				member.setSpareName2(couponActivityAddress.getConsignmentName());
				member.setSpareTel2(couponActivityAddress.getConsignmentPhone());
				mybatisDao.update(member);
			}
		};
	}
	
	@Transactional
	public void updateMember(Integer id,UpdateMember updateMember) {
		CdMember member = new CdMember();
		member.setId(id);
		member.setMemberType(updateMember.getMemberType());
		member.setName(updateMember.getName());
		member.setPhoneNumber(updateMember.getPhoneNumber());
		member.setSpareName(updateMember.getSpareName());
		member.setSpareName2(updateMember.getSpareName2());
		member.setSpareTel(updateMember.getSpareTel());
		member.setSpareTel2(updateMember.getSpareTel2());
		member.setTelNumber(updateMember.getTelNumber());
		member.setOfficeTel(updateMember.getOfficeTel());
		member.setAddress(updateMember.getAddress());
		member.setSpareAddress(updateMember.getSpareAddress());
		member.setSpareAddress2(updateMember.getSpareAddress2());
		member.setCdCompanyId(updateMember.getCdCompanyId());
		member.setCdUserId(updateMember.getCdUserId());
		mybatisDao.update(member);
	}
	
	@Transactional
    public void delete(Integer id){
		//删除卡与客户的绑定关系
		CdMemberCouponRelationExample tbOrderItemExample = new CdMemberCouponRelationExample();
        tbOrderItemExample.createCriteria().andCdMemberIdEqualTo(id);
        mybatisDao.deleteByExample(tbOrderItemExample);
        mybatisDao.deleteByPrimaryKey(CdMember.class, id);
    }

}
