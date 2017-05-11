package com.xmomen.module.base.service.impl;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.*;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.model.MemberModel;
import com.xmomen.module.base.model.UpdateMember;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.base.service.MemberSercvice;
import com.xmomen.module.member.entity.MemberAddress;
import com.xmomen.module.member.entity.MemberAddressExample;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.member.model.MemberAddressUpdate;
import com.xmomen.module.wx.module.cart.service.CartService;
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

    @Autowired
    CartService cartService;

    public CdMember getOneMemberModel(String id) {
        return this.mybatisDao.selectByPrimaryKey(CdMember.class, id);
    }

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
     * @param openId
     */
    @Transactional
    public CdMember bindMember(String openId) {
        CdMember member = new CdMember();
        member = mybatisDao.insertByModel(member);
        //新增绑定关系
        CdBind bind = new CdBind();
        bind.setUserId(member.getId());
        bind.setOpenId(openId);
        List<CdBind> cdBinds = mybatisDao.selectByModel(bind);
        this.mybatisDao.save(bind);

        return member;
    }

    /**
     * 绑定
     *
     * @param mobile
     * @param name
     * @param memberId
     */
    @Transactional
    public CdMember bindMember(String mobile, String name, String openId, Integer memberId) throws Exception {
        //判断openid是否绑定
        CdBind binddb = new CdBind();
        binddb.setOpenId(openId);
        List<CdBind> cdBinds = mybatisDao.selectByModel(binddb);
        if (cdBinds.size() > 0) {
            throw new BusinessException("微信账号已经绑定手机号：" + cdBinds.get(0).getPhone());
        }
        //手机号是否在member表存在 不存在则新增
        CdMember member = new CdMember();
        member.setPhoneNumber(mobile);
        List<CdMember> members = mybatisDao.selectByModel(member);
        //手机号不存在场合
        if (members.size() == 0) {
            //更新member
            CdMember cdMember = new CdMember();
            cdMember.setId(memberId);
            cdMember.setName(name);
            cdMember.setPhoneNumber(mobile);
            member = mybatisDao.updateByModel(cdMember);

            //更新cdBind
            CdBindExample cdBindExample = new CdBindExample();
            cdBindExample.createCriteria().andUserIdEqualTo(memberId);
            CdBind cdBind = new CdBind();
            //手机号
            cdBind.setPhone(mobile);
            mybatisDao.updateOneByExampleSelective(cdBind, cdBindExample);
        }
        //手机号存在场合
        else {
            member = members.get(0);
            //替换购物车数据
            cartService.copyCartInfo(String.valueOf(memberId), String.valueOf(member.getId()));

            //删除新的member
            mybatisDao.deleteByPrimaryKey(CdMember.class, memberId);

            //新增绑定关系
            CdBind bind = new CdBind();
            bind.setUserId(member.getId());
            bind.setPhone(mobile);
            bind.setOpenId(openId);
            mybatisDao.save(bind);
        }

        return member;
    }

    /**
     * 更新手机号码
     *
     * @param id     主键
     * @param mobile 新手机号码
     */
    @Transactional
    public void updateMobile(Integer id, String mobile) {
        CdMember memberDb = this.mybatisDao.selectByPrimaryKey(CdMember.class, id);
        //新手机号码和原来一样 直接返回
        if (memberDb.getPhoneNumber().equals(mobile)) {
            return;
        }
        //判断新手机是否存在 存在则不能修改
        CdMember member = new CdMember();
        member.setPhoneNumber(mobile);
        List<CdMember> members = mybatisDao.selectByModel(member);
        if (members.size() > 0) {
            throw new IllegalArgumentException("新手机号已经绑定其他账号!");
        }
        //更新bind表的手机号
        CdBind bind = new CdBind();
        bind.setUserId(id);
        List<CdBind> cdBinds = mybatisDao.selectByModel(bind);
        //更新手机号
        if (cdBinds.size() > 0) {
            bind = cdBinds.get(0);
            bind.setPhone(mobile);
            mybatisDao.updateByModel(bind);
        }
        else {
            throw new IllegalArgumentException("新手机号已经绑定其他账号!");
        }
        //更新用户表的手机号
        member.setId(id);
        this.mybatisDao.updateByModel(member);
    }
}
