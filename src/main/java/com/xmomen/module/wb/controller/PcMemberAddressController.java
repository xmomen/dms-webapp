package com.xmomen.module.wb.controller;
	
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.service.MemberAddressService;
import com.xmomen.module.wb.model.PcMemberAddressModel;

@RestController
@RequestMapping(value = "/wb/memberAddress")
public class PcMemberAddressController {

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
    public List<MemberAddressModel> getMemberAddressList(@RequestParam(value = "id", required = false) String id,
                                                         @RequestParam(value = "ids", required = false) String[] ids,
                                                         @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        MemberAddressQuery memberAddressQuery = new MemberAddressQuery();
        memberAddressQuery.setId(id);
        memberAddressQuery.setExcludeIds(excludeIds);
        memberAddressQuery.setIds(ids);
        Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        memberAddressQuery.setCdMemberId(String.valueOf(memberId));
        return memberAddressService.getMemberAddressModels(memberAddressQuery);
    }

    /**
     * 查询单个客户地址
     *
     * @param id 主键
     * @return MemberAddressModel   客户地址领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    public MemberAddressModel createMemberAddress(@RequestBody @Valid PcMemberAddressModel pcMemberAddressModel) {
    	MemberAddressModel memberAddressModel = new MemberAddressModel();
        BeanUtils.copyProperties(pcMemberAddressModel, memberAddressModel);
        Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        memberAddressModel.setCdMemberId(memberId);
        return memberAddressService.createMemberAddress(memberAddressModel);
    }

    /**
     * 更新客户地址
     *
     * @param id                 主键
     * @param memberAddressModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateMemberAddress(@PathVariable(value = "id") String id,
                                    @RequestBody @Valid PcMemberAddressModel pcMemberAddressModel) {
    	MemberAddressModel memberAddressModel = new MemberAddressModel();
        BeanUtils.copyProperties(pcMemberAddressModel, memberAddressModel);
        Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        memberAddressModel.setCdMemberId(memberId);
        memberAddressService.updateMemberAddress(memberAddressModel);
    }

    /**
     * 删除客户地址
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMemberAddress(@PathVariable(value = "id") String id) {
        memberAddressService.deleteMemberAddress(id);
    }

    /**
     * 删除客户地址
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteMemberAddresss(@RequestParam(value = "ids") String[] ids) {
        memberAddressService.deleteMemberAddress(ids);
    }
}
