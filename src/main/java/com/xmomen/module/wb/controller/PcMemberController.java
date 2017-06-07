package com.xmomen.module.wb.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberService;

@RestController
@RequestMapping(value = "/wb/member")
public class PcMemberController {

	@Autowired
    MemberService memberSercvice;


    /**
     * 更新客户手机号
     *
     * @param mobile   新手机号码
     */
    @RequestMapping(value = "/updateMobile", method = RequestMethod.GET)
    public void updateMemberAddress(@RequestParam("mobile") String mobile) {
    	Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        memberSercvice.updateMobile(memberId, mobile);
    }

    /**
     * 查询单个客户
     *
     * @param id 主键
     * @return CdMember   客户领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CdMember getMemberById(@PathVariable(value = "id") String id) {
        return memberSercvice.getOneMemberModel(id);
    }

    /**
     * 查询单个客户
     *
     * @param id 主键
     * @return CdMember   客户领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CdMember getMemberById(@PathVariable(value = "id") String id,
                                  @RequestBody CdMember cdMember) {
        return memberSercvice.updateMember(cdMember);
    }


}
