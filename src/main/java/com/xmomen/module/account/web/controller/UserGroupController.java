package com.xmomen.module.account.web.controller;

import com.xmomen.module.account.service.RoleService;
import com.xmomen.module.account.service.UserService;
import com.xmomen.module.account.web.controller.vo.CreateUserGroup;
import com.xmomen.module.account.web.controller.vo.GroupPermissionRelation;
import com.xmomen.module.account.web.controller.vo.UpdateUserGroup;
import com.xmomen.module.account.web.controller.vo.UserGroupRelation;
import com.xmomen.module.user.entity.SysRoles;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class UserGroupController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    /**
     * 查询用户组列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    @Log(actionName = "查询用户组列表")
    public Page<SysRoles> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        keyword = StringUtils.trimToEmpty(keyword);
        return roleService.findRoles("%" + keyword + "%", limit, offset);
    }

    /**
     *  查询用户组
     * @param id
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询用户组")
    public SysRoles getUserList(@PathVariable(value = "id") Integer id){
        return roleService.getRole(id);
    }

    /**
     * 新增用户组
     * @param createUserGroup
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    @Log(actionName = "新增用户组")
    public void createUser(@RequestBody @Valid CreateUserGroup createUserGroup, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysRoles sysRoles = new SysRoles();
        sysRoles.setRole(createUserGroup.getUserGroup());
        sysRoles.setDescription(createUserGroup.getDescription());
        sysRoles.setAvailable(createUserGroup.getAvailable() != null && createUserGroup.getAvailable() ? 1 : 0);
        roleService.createRole(sysRoles);
    }

    /**
     *  删除用户组
     * @param id
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除用户组")
    public void deleteUser(@PathVariable(value = "id") Integer id){
        roleService.deleteRole(id);
    }

    /**
     *  修改用户组
     * @param id
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改用户组")
    public void updateUserGroup(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateUserGroup updateUserGroup, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysRoles sysRoles = new SysRoles();
        sysRoles.setId(id);
        sysRoles.setDescription(updateUserGroup.getDescription());
        if(updateUserGroup.getAvailable() != null){
            sysRoles.setAvailable(updateUserGroup.getAvailable() ? 1 : 0);
        }
        if(updateUserGroup.getUserIdList() != null && updateUserGroup.getUserIdList().size() > 0){
            roleService.updateRole(sysRoles);
        }else{
            roleService.updateRole(sysRoles, updateUserGroup.getUserIdList());
        }
    }

    /**
     * 查询用户组用户
     * @param groupId
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/group/{groupId}/user")
    @Log(actionName = "查询用户组下属用户")
    public Page<UserGroupRelation> findUsersByGroup(@PathVariable(value = "groupId") String groupId,
                                       @RequestParam(value = "chose") Boolean unChose,
                                       @RequestParam(value = "limit") Integer limit,
                                       @RequestParam(value = "offset") Integer offset){
        if(unChose == null){
            unChose = false;
        }
        return roleService.findUsersByRoles(groupId, unChose, limit, offset);
    }

    /**
     * 绑定用户
     * @param groupId
     * @param chose
     * @param userId
     */
    @Log(actionName = "修改用户组下属用户关系")
    @RequestMapping(value = "/group/{groupId}/user", method = RequestMethod.PUT)
    public void findUsersByGroup(@PathVariable(value = "groupId") Integer groupId,
                                 @RequestParam(value = "chose") Boolean chose,
                                 @RequestParam(value = "userId") Integer userId){
        if(chose != null && chose){
            userService.correlationRoles(userId, groupId);
        }else{
            userService.uncorrelationRoles(userId, groupId);
        }
    }

    /**
     * 查询用户组权限
     * @param groupId
     * @param limit
     * @param offset
     * @return
     */
    @Log(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/group/{groupId}/permissions")
    public Page<GroupPermissionRelation> findPermissionByGroup(@PathVariable(value = "groupId") String groupId,
                                                    @RequestParam(value = "chose") Boolean unChose,
                                                    @RequestParam(value = "limit") Integer limit,
                                                    @RequestParam(value = "offset") Integer offset){
        if(unChose == null){
            unChose = false;
        }
        return roleService.findPermissionByRoles(groupId, unChose, limit, offset);
    }

    /**
     * 绑定权限
     * @param groupId
     * @param chose
     * @param permissionId
     */
    @Log(actionName = "修改用户组所属权限")
    @RequestMapping(value = "/group/{groupId}/permissions", method = RequestMethod.PUT)
    public void correlationPermission(@PathVariable(value = "groupId") Integer groupId,
                                 @RequestParam(value = "chose") Boolean chose,
                                 @RequestParam(value = "permissionId") Integer permissionId){
        if(chose != null && chose){
            roleService.correlationPermissions(groupId, permissionId);
        }else{
            roleService.unCorrelationPermissions(groupId, permissionId);
        }
    }

}
