package com.xmomen.module.account.web.controller;

import com.xmomen.module.account.service.PermissionService;
import com.xmomen.module.account.service.RoleService;
import com.xmomen.module.account.service.UserService;
import com.xmomen.module.account.web.controller.vo.CreatePermissionVo;
import com.xmomen.module.user.entity.SysPermissions;
import com.xmomen.module.user.entity.SysPermissionsExample;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
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
    RoleService roleService;

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
        Set<String> permissions = userService.findPermissions(username);
        Map rolesMap = new HashMap();
        rolesMap.put("roles", roles);
        rolesMap.put("permissions", permissions);
        return rolesMap;
    }

    /**
     * 权限列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    @Log(actionName = "查看权限列表")
    public Page<SysPermissions> getPermissionList(@RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "offset") Integer offset,
                                        @RequestParam(value = "keyword", required = false) String keyword){
        SysPermissionsExample sysPermissionsExample = new SysPermissionsExample();
        sysPermissionsExample.createCriteria()
                .andPermissionLike("%" + StringUtils.trimToEmpty(keyword) + "%");
        sysPermissionsExample.or()
                .andDescriptionLike("%" + StringUtils.trimToEmpty(keyword) + "%");
        return mybatisDao.selectPageByExample(sysPermissionsExample, limit, offset);
    }

    /**
     *  权限资源
     * @param id
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询单个权限")
    public SysPermissions getPermission(@PathVariable(value = "id") Integer id){
        return mybatisDao.selectByPrimaryKey(SysPermissions.class, id);
    }

    /**
     * 新增权限
     * @param createPermissionVo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    @Log(actionName = "新增权限资源")
    public SysPermissions createPermission(@RequestBody @Valid CreatePermissionVo createPermissionVo, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysPermissions sysPermissions = new SysPermissions();
        sysPermissions.setDescription(createPermissionVo.getDescription());
        sysPermissions.setPermission(createPermissionVo.getPermissionCode().toUpperCase());
        sysPermissions.setAvailable(createPermissionVo.getAvailable() != null && createPermissionVo.getAvailable() ? 1 : 0);
        return permissionService.createPermission(sysPermissions);
    }

    /**
     *  删除权限
     * @param id
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除权限资源")
    public void deletePermission(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(SysPermissions.class, id);
    }

}
