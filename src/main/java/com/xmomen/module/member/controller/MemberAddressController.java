package com.xmomen.module.member.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.service.MemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-3-23 14:57:22
 */
@RestController
@RequestMapping(value = "/memberAddress")
public class MemberAddressController {

    @Autowired
    MemberAddressService memberAddressService;

    /**
     * 客户地址列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<MemberAddressModel> 客户地址领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询客户地址列表")
    public Page<MemberAddressModel> getMemberAddressList(@RequestParam(value = "limit") Integer limit,
                                                         @RequestParam(value = "offset") Integer offset,
                                                         @RequestParam(value = "id", required = false) String id,
                                                         @RequestParam(value = "ids", required = false) String[] ids,
                                                         @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        MemberAddressQuery memberAddressQuery = new MemberAddressQuery();
        memberAddressQuery.setId(id);
        memberAddressQuery.setExcludeIds(excludeIds);
        memberAddressQuery.setIds(ids);
        return memberAddressService.getMemberAddressModelPage(limit, offset, memberAddressQuery);
    }

    /**
     * 查询单个客户地址
     *
     * @param id 主键
     * @return MemberAddressModel   客户地址领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询客户地址")
    public MemberAddressModel getMemberAddressById(@PathVariable(value = "id") String id) {
        return memberAddressService.getOneMemberAddressModel(id);
    }

    /**
     * 新增客户地址
     *
     * @param memberAddressModel 新增对象参数
     * @return MemberAddressModel   客户地址领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增客户地址")
    public MemberAddressModel createMemberAddress(@RequestBody @Valid MemberAddressModel memberAddressModel) {
        return memberAddressService.createMemberAddress(memberAddressModel);
    }

    /**
     * 更新客户地址
     *
     * @param id                 主键
     * @param memberAddressModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新客户地址")
    public void updateMemberAddress(@PathVariable(value = "id") String id,
                                    @RequestBody @Valid MemberAddressModel memberAddressModel) {
        memberAddressService.updateMemberAddress(memberAddressModel);
    }

    /**
     * 删除客户地址
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个客户地址")
    public void deleteMemberAddress(@PathVariable(value = "id") String id) {
        memberAddressService.deleteMemberAddress(id);
    }

    /**
     * 删除客户地址
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除客户地址")
    public void deleteMemberAddresss(@RequestParam(value = "ids") String[] ids) {
        memberAddressService.deleteMemberAddress(ids);
    }


}
