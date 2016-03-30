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
		
	}
	
	@Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(CdMember.class, id);
    }

}
