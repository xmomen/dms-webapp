package com.xmomen.module.wb.controller;

import java.util.List;

import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.model.MemberAddressQuery;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.member.entity.MemberAddress;
import com.xmomen.module.member.service.MemberAddressService;
import com.xmomen.module.order.service.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/wb/address")
public class PcAddressController extends PcBaseController {

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
    @RequestMapping(value = "/defaultAddress", method = RequestMethod.GET)
    @ResponseBody
    public Boolean defaultAddress(
            @RequestParam(value = "addressId") String addressId) {
        memberAddressService.defaultAddress(addressId);
        return Boolean.TRUE;
    }

    /**
     * 客户地址列表
     *
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
        memberAddressQuery.setCdMemberId(String.valueOf(getCurrentMemberId()));
        return memberAddressService.getMemberAddressModels(memberAddressQuery);
    }


    /**
     * 新增客户地址
     *
     * @param memberAddressModel 新增对象参数
     * @return MemberAddressModel   客户地址领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    public MemberAddressModel createMemberAddress(@RequestBody @Valid MemberAddressModel memberAddressModel) {
        return memberAddressService.createMemberAddress(memberAddressModel);
    }

    /**
     * 获取默认收货地址
     *
     * @param memberId 客户ID
     * @return
     */
    @RequestMapping(value = "/getDefaultAddress", method = RequestMethod.GET)
    @ResponseBody
    public MemberAddress getDefaultAddress() {
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setCdMemberId(getCurrentMemberId());
        memberAddress.setIsDefault(true);
        List<MemberAddress> memberAddresses = this.mybatisDao.selectByModel(memberAddress);
        if (memberAddresses.size() > 0) {
            return memberAddresses.get(0);
        }
        return null;
    }
}
