package com.xmomen.module.wb.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.member.entity.MemberAddress;
import com.xmomen.module.member.service.MemberAddressService;
import com.xmomen.module.order.service.OrderService;

@RestController
public class PcAddressController {

	@Autowired
    MemberAddressService memberAddressService;

    @Autowired
    OrderService orderService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 默认收货地址
     *
     * @param addressId 收货地址
     * @return
     */
    @RequestMapping(value = "/wb/defaultAddress", method = RequestMethod.GET)
    @ResponseBody
    public Boolean defaultAddress(
            @RequestParam(value = "addressId") String addressId) {
        memberAddressService.defaultAddress(addressId);
        return Boolean.TRUE;
    }


    /**
     * 获取默认收货地址
     *
     * @param memberId 客户ID
     * @return
     */
    @RequestMapping(value = "/wb/getDefaultAddress", method = RequestMethod.GET)
    @ResponseBody
    public MemberAddress getDefaultAddress() {
    	Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setCdMemberId(memberId);
        memberAddress.setIsDefault(true);
        List<MemberAddress> memberAddresses = this.mybatisDao.selectByModel(memberAddress);
        if (memberAddresses.size() > 0) {
            return memberAddresses.get(0);
        }
        return null;
    }
}
