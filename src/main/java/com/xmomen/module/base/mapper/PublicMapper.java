package com.xmomen.module.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.xmomen.module.user.entity.SysUsers;

public interface PublicMapper {
	@Select(value = "select su.* from sys_users su left join sys_users_roles sur on sur.USER_ID = su.ID left join sys_roles sr on sr.id = sur.ROLE_ID where sr.ROLE ='customer_manager'")
    @ResultType(SysUsers.class)
    public List<SysUsers> getCustomerManager();
}
