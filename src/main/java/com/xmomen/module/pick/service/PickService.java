package com.xmomen.module.pick.service;

import com.xmomen.module.pick.model.CreateMember;
import com.xmomen.module.pick.model.PickVo;

public interface PickService {
	public void pick(PickVo pickVo);
	
	public void pickCard(CreateMember createMember);
}
