package com.udfex.ams.module.account.web.controller;

import com.udfex.ams.module.account.model.CreateUser;
import com.udfex.ams.module.account.service.UserService;
import com.udfex.ams.module.account.web.controller.vo.CreateUserVo;
import com.udfex.ucs.module.user.entity.SysPermissions;
import com.udfex.ucs.module.user.entity.SysUsers;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     *  用户列表
     * @param id
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public SysPermissions getUserList(@PathVariable(value = "id") Integer id){
        return mybatisDao.selectByPrimaryKey(SysPermissions.class, id);
    }

    /**
     * 新增用户
     * @param createUser
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public SysUsers createUser(@RequestBody @Valid CreateUserVo createUser, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CreateUser user = new CreateUser();
        user.setUsername(createUser.getUsername());
        user.setPassword(createUser.getPassword());
        user.setEmail(createUser.getEmail());
        user.setLocked(createUser.getLocked() != null && createUser.getLocked() == true ? true : false);
        return userService.createUser(user);
    }

    /**
     *  删除用户
     * @param id
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(SysPermissions.class, id);
    }

}
