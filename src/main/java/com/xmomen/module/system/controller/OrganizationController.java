package com.xmomen.module.system.controller;

import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.account.web.controller.vo.CreateUserGroup;
import com.xmomen.module.account.web.controller.vo.UpdateUserGroup;
import com.xmomen.module.logger.Log;
import com.xmomen.module.system.entity.SysOrganization;
import com.xmomen.module.system.model.CreateOrganization;
import com.xmomen.module.system.model.OrganizationModel;
import com.xmomen.module.system.service.OrganizationService;
import com.xmomen.module.user.entity.SysRoles;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jeng on 16/3/25.
 */
@RestController
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    /**
     * 查询组织机构信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    @Log(actionName = "查询组织机构信息")
    public List<OrganizationModel> getOrganizationTree(@RequestParam(value = "id", required = false) Integer id){
        return organizationService.getOrganizationTree(id);
    }

    /**
     * 新增机构组织
     * @param createOrganization
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @Log(actionName = "新增组织机构")
    public void createOrganization(@RequestBody @Valid CreateOrganization createOrganization, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysOrganization sysOrganization = new SysOrganization();
        sysOrganization.setName(createOrganization.getName());
        sysOrganization.setDescription(createOrganization.getDescription());
        sysOrganization.setParentId(createOrganization.getParentId());
        organizationService.createOrganization(sysOrganization);
    }

    /**
     *  删除机构组织
     * @param id
     */
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除组织机构")
    public void deleteOrganization(@PathVariable(value = "id") Integer id){
        organizationService.delete(id);
    }

    /**
     *  修改机构组织
     * @param id
     */
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改组织机构")
    public void updateOrganization(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid CreateOrganization createOrganization, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysOrganization sysOrganization = new SysOrganization();
        sysOrganization.setId(id);
        sysOrganization.setName(createOrganization.getName());
        sysOrganization.setDescription(createOrganization.getDescription());
        sysOrganization.setParentId(createOrganization.getParentId());
        organizationService.updateOrganization(sysOrganization);
    }

    /**
     * 新增机构组织用户
     * @param id
     * @param userIds
     */
    @RequestMapping(value = "/organization/{id}/user", method = RequestMethod.POST)
    @Log(actionName = "新增组织机构用户")
    public void bindOrganizationUser(@PathVariable(value = "id") Integer id,
                                   @RequestParam(value = "userIds") Integer[] userIds) {

        organizationService.bindOrganizationUser(id, userIds);
    }

    /**
     * 删除机构组织用户
     * @param id
     * @param userIds
     */
    @RequestMapping(value = "/organization/{id}/user", method = RequestMethod.DELETE)
    @Log(actionName = "删除组织机构用户")
    public void updateOrganization(@PathVariable(value = "id") Integer id,
                                   @RequestParam(value = "userIds") Integer[] userIds) {
        organizationService.unBindOrganizationUser(id, userIds);
    }
}
