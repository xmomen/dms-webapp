package com.xmomen.module.wb.controller;
	
import java.util.List;

import javax.validation.Valid;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.member.entity.MemberAddress;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.service.MemberAddressService;
import com.xmomen.module.wb.model.PcMemberAddressModel;

@RestController
@RequestMapping(value = "/wb/memberAddress")
public class PcMemberAddressController extends PcBaseController {

	@Autowired
    MemberAddressService memberAddressService;

    @Autowired
    MybatisDao mybatisDao;

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
        memberAddressModel.setCdMemberId(getCurrentMemberId());
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
        memberAddressModel.setCdMemberId(getCurrentMemberId());
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

    /**
     * 默认收货地址
     *
     * @param addressId 收货地址
     * @return
     */
    @RequestMapping(value = "/default", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean defaultAddress(
            @RequestParam(value = "addressId") String addressId) {
        memberAddressService.defaultAddress(addressId);
        return Boolean.TRUE;
    }


    /**
     * 获取默认收货地址
     *
     * @return
     */
    @RequestMapping(value = "/default", method = RequestMethod.GET)
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
