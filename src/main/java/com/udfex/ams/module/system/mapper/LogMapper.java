package com.udfex.ams.module.system.mapper;

import com.udfex.ams.module.system.model.LogModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Jeng on 16/3/20.
 */
public interface LogMapper {

    @Insert("insert into sys_log(user_id,action_name,action_date,action_params,client_ip,action_result) values(#{userId},#{actionName},#{actionDate},#{actionParams},#{clientIp},#{actionResult}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertLog(LogModel logModel);
}
