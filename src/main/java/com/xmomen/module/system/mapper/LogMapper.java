package com.xmomen.module.system.mapper;

import com.xmomen.module.system.model.LogModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * Created by Jeng on 16/3/20.
 */
public interface LogMapper {

    @Insert("insert into sys_log(user_id,action_name,action_date,action_params,client_ip,action_result) values(#{userId},#{actionName},#{actionDate},#{actionParams},#{clientIp},#{actionResult}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertLog(LogModel logModel);
}
