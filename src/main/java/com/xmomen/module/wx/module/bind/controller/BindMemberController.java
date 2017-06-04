package com.xmomen.module.wx.module.bind.controller;

import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BindMemberController {

    @Autowired
    MemberService memberSercvice;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/wx/bindMember", method = RequestMethod.PUT)
    @ResponseBody
    public CdMember bindMember(
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "openId") String openId,
            @RequestParam(value = "memberId") Integer memberId) throws Exception {
        return memberSercvice.bindMember(mobile, name, openId, memberId);
    }
}
