package com.xmomen.module.base.member.service;

import com.xmomen.module.base.member.model.CreateMember;
import com.xmomen.module.base.member.model.UpdateMember;

public interface MemberSercvice {
	public void createMember(CreateMember createMember);
	
	public void updateMember(Integer id,UpdateMember updateMember);
	
	public void delete(Integer id);
}
