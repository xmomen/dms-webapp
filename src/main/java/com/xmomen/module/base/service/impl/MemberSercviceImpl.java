package com.xmomen.module.base.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.*;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.model.UpdateMember;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.base.service.MemberSercvice;
import com.xmomen.module.member.entity.MemberAddress;
import com.xmomen.module.member.entity.MemberAddressExample;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.member.model.MemberAddressUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (member == null) {
            member = new CdMember();
            member.setMemberType(createMember.getMemberType());
            member.setName(createMember.getName());
            member.setPhoneNumber(createMember.getPhoneNumber());
            member.setTelNumber(createMember.getTelNumber());
            member.setOfficeTel(createMember.getOfficeTel());
            member.setCdCompanyId(createMember.getCdCompanyId());
            member.setCdUserId(createMember.getCdUserId());
            member = mybatisDao.insertByModel(member);
            //保存收货地址
            for (MemberAddressCreate memberAddressCreate : createMember.getMemberAddressList()) {
                MemberAddress memberAddress = new MemberAddress();
                memberAddress.setCdMemberId(member.getId());
                memberAddress.setAddress(memberAddressCreate.getAddress());
                memberAddress.setFullAddress(memberAddressCreate.getAddress());
                memberAddress.setMobile(memberAddressCreate.getMobile());
                memberAddress.setName(memberAddressCreate.getName());
                mybatisDao.insertByModel(memberAddress);
            }
        }
        //存在场合
        else {
            member.setMemberType(createMember.getMemberType());
            member.setName(createMember.getName());
            member.setPhoneNumber(createMember.getPhoneNumber());
            member.setTelNumber(createMember.getTelNumber());
            member.setOfficeTel(createMember.getOfficeTel());
            member.setCdCompanyId(createMember.getCdCompanyId());
            member.setCdUserId(createMember.getCdUserId());
            member = mybatisDao.updateByModel(member);
            //保存收货地址
            for (MemberAddressCreate memberAddressCreate : createMember.getMemberAddressList()) {
                MemberAddress memberAddress = new MemberAddress();
                memberAddress.setCdMemberId(member.getId());
                memberAddress.setAddress(memberAddressCreate.getAddress());
                memberAddress.setFullAddress(memberAddressCreate.getAddress());
                memberAddress.setMobile(memberAddressCreate.getMobile());
                memberAddress.setName(memberAddressCreate.getName());
                mybatisDao.insertByModel(memberAddress);
            }
        }
        if (!StringUtils.isBlank(createMember.getCouponNumber())) {
            CdMemberCouponRelation cdMemberCouponRelation = new CdMemberCouponRelation();
            cdMemberCouponRelation.setCdMemberId(member.getId());
            cdMemberCouponRelation.setCouponNumber(createMember.getCouponNumber());
            mybatisDao.insert(cdMemberCouponRelation);
            //查看卡是否有送礼品地址 如果有填充到第3个地址里面
            CdActivityAddress couponActivityAddress = new CdActivityAddress();
            couponActivityAddress.setCouponNumber(createMember.getCouponNumber());
            List<CdActivityAddress> couponAddressList = mybatisDao.selectByModel(couponActivityAddress);
            if (couponAddressList.size() > 0) {
                couponActivityAddress = couponAddressList.get(0);
                member.setSpareAddress2(couponActivityAddress.getConsignmentAddress());
                member.setSpareName2(couponActivityAddress.getConsignmentName());
                member.setSpareTel2(couponActivityAddress.getConsignmentPhone());
                mybatisDao.update(member);
            }
        }
        ;
    }

    @Transactional
    public void updateMember(Integer id, UpdateMember updateMember) {
        CdMember member = new CdMember();
        member.setId(id);
        member.setMemberType(updateMember.getMemberType());
        member.setName(updateMember.getName());
        member.setPhoneNumber(updateMember.getPhoneNumber());
        member.setTelNumber(updateMember.getTelNumber());
        member.setOfficeTel(updateMember.getOfficeTel());
        member.setCdCompanyId(updateMember.getCdCompanyId());
        member.setCdUserId(updateMember.getCdUserId());
        mybatisDao.update(member);
        //保存收货地址
        for (MemberAddressUpdate memberAddressUpdate : updateMember.getMemberAddressList()) {
            MemberAddress memberAddress = new MemberAddress();
            if (StringUtils.isNotEmpty(memberAddressUpdate.getId())) {
                memberAddress.setId(memberAddressUpdate.getId());
                memberAddress.setCdMemberId(id);
                memberAddress.setAddress(memberAddressUpdate.getAddress());
                memberAddress.setFullAddress(memberAddressUpdate.getAddress());
                memberAddress.setMobile(memberAddressUpdate.getMobile());
                memberAddress.setName(memberAddressUpdate.getName());
                mybatisDao.update(memberAddress);
            }
            else {
                memberAddress.setCdMemberId(id);
                memberAddress.setFullAddress(memberAddressUpdate.getAddress());
                memberAddress.setMobile(memberAddressUpdate.getMobile());
                memberAddress.setAddress(memberAddressUpdate.getAddress());
                memberAddress.setName(memberAddressUpdate.getName());
                mybatisDao.insert(memberAddress);
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        //删除卡与客户的绑定关系
        CdMemberCouponRelationExample tbOrderItemExample = new CdMemberCouponRelationExample();
        tbOrderItemExample.createCriteria().andCdMemberIdEqualTo(id);
        mybatisDao.deleteByExample(tbOrderItemExample);
        mybatisDao.deleteByPrimaryKey(CdMember.class, id);
        //删除收货地址
        MemberAddressExample memberAddressExample = new MemberAddressExample();
        memberAddressExample.createCriteria().andCdMemberIdEqualTo(id);
        mybatisDao.deleteByExample(memberAddressExample);
    }

    /**
     * 绑定
     *
     * @param mobile
     * @param openId
     */
    public void bindMember(String mobile, String openId) {
        CdBind bind = new CdBind();
        bind.setPhone(mobile);
        bind.setOpenId(openId);
        this.mybatisDao.save(bind);
        //手机号是否在member表存在 不存在则新增
        CdMember member = new CdMember();
        member.setPhoneNumber(mobile);
        List<CdMember> members = mybatisDao.selectByModel(member);
        if (members.size() == 0) {
            member = new CdMember();
            member.setPhoneNumber(mobile);
            member = mybatisDao.insertByModel(member);
        }
    }
}
