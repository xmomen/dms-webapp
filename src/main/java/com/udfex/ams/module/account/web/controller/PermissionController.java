package com.udfex.ams.module.account.web.controller;

import com.udfex.ams.module.account.service.UserService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class PermissionController {

    @Autowired
    UserService userService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 用户权限
     * @return
     */
    @RequestMapping(value = "/user/permissions", method = RequestMethod.GET)
    public Map getPermission(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Set<String> roles = userService.findRoles(username);
        Map map = new HashMap();
        map.put("roles", roles);
        return map;
    }

}
