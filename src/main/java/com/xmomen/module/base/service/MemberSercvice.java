package com.xmomen.module.base.service;

import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.model.UpdateMember;

public interface MemberSercvice {
	public void createMember(CreateMember createMember);
	
	public void updateMember(Integer id,UpdateMember updateMember);
	
	public void delete(Integer id);

}
