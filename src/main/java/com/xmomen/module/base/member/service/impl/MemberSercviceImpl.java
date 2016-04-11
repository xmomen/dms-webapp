package com.xmomen.module.base.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.member.model.CreateMember;
import com.xmomen.module.base.member.model.UpdateMember;
import com.xmomen.module.base.member.service.MemberSercvice;
import com.xmomen.module.system.entity.SysOrganization;
@Service
public class MemberSercviceImpl implements MemberSercvice {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createMember(CreateMember createMember) {
		CdMember member = new CdMember();
		member.setMemberCode(createMember.getMemberCode());
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
		mybatisDao.save(member);
	}
	@Transactional
	public void updateMember(Integer id,UpdateMember updateMember) {
		CdMember member = new CdMember();
		member.setId(id);
		member.setMemberCode(updateMember.getMemberCode());
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
        mybatisDao.deleteByPrimaryKey(CdMember.class, id);
    }

}
