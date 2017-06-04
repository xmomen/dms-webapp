package com.xmomen.module.wx.controller;

import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-3-29 0:27:52
 */
@RestController
@RequestMapping(value = "/wx/member")
public class WxMemberController {

    @Autowired
    MemberService memberSercvice;


    /**
     * 更新客户手机号
     *
     * @param memberId member主键
     * @param mobile   新手机号码
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateMemberAddress(@PathVariable("id") Integer memberId,
                                    @RequestParam("mobile") String mobile) {
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

}
