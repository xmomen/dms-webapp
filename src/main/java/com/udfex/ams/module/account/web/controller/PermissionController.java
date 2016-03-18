package com.udfex.ams.module.account.web.controller;

import com.udfex.ams.module.account.model.CreateUser;
import com.udfex.ams.module.account.service.PermissionService;
import com.udfex.ams.module.account.service.UserService;
import com.udfex.ams.module.account.web.controller.vo.CreatePermissionVo;
import com.udfex.ams.module.account.web.controller.vo.CreateUserVo;
import com.udfex.ucs.module.user.entity.SysPermissions;
import com.udfex.ucs.module.user.entity.SysPermissionsExample;
import com.udfex.ucs.module.user.entity.SysUsers;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import org.apache.commons.lang.StringUtils;
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
    PermissionService permissionService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 权限权限
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
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public Page<SysPermissions> getPermissionList(@RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "offset") Integer offset,
                                        @RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam(value = "keyword", required = false) String keyword){
        SysPermissionsExample sysPermissionsExample = new SysPermissionsExample();
        sysPermissionsExample.createCriteria()
                .andPermissionLike("%" + StringUtils.trimToEmpty(keyword) + "%")
                .andDescriptionLike("%" + StringUtils.trimToEmpty(keyword) + "%");
        return mybatisDao.selectPageByExample(sysPermissionsExample, limit, offset);
    }

    /**
     *  权限列表
     * @param id
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public SysPermissions getPermission(@PathVariable(value = "id") Integer id){
        return mybatisDao.selectByPrimaryKey(SysPermissions.class, id);
    }

    /**
     * 新增权限
     * @param createUser
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public SysPermissions createPermission(@RequestBody @Valid CreatePermissionVo createPermissionVo, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysPermissions sysPermissions = new SysPermissions();
        sysPermissions.setDescription(createPermissionVo.getDescription());
        sysPermissions.setPermission(createPermissionVo.getPermissionCode());
        sysPermissions.setAvailable(createPermissionVo.getAvailable() ? 1 : 0);
        return permissionService.createPermission(sysPermissions);
    }

    /**
     *  删除权限
     * @param id
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public void deletePermission(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(SysPermissions.class, id);
    }

}
