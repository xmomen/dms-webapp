package com.xmomen.module.wx.module.bind;

import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberSercvice;
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
    MemberSercvice memberSercvice;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/wx/bindMember", method = RequestMethod.PUT)
    @ResponseBody
    public CdMember myOrder(
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "openId") String openId) {
        return memberSercvice.bindMember(mobile, openId);
    }
}
