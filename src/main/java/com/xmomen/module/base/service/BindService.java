package com.xmomen.module.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdBind;

/**
 * Created by Jeng on 2016/3/30.
 */
@Service
public class BindService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 绑定手机号
     * 如果之前绑定过则覆盖
     * @param openId
     * @param phone
     * @return
     */
	public boolean bindAccount(String openId, String phone){
		CdBind bind = new CdBind();
		bind.setOpenId(openId);
		List<CdBind> binds = mybatisDao.selectByModel(bind);
		if(binds != null && binds.size() > 0){
			bind.setId(binds.get(0).getId());;
		}
		bind.setOpenId(openId);
		bind.setPhone(phone);
		mybatisDao.save(bind);
		return true;
	}
}
