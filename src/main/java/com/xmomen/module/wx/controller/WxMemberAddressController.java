package com.xmomen.module.wx.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.logger.Log;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.service.MemberAddressService;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-3-29 0:27:52
 */
@RestController
@RequestMapping(value = "/wx/memberAddress")
public class WxMemberAddressController {

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
                                                         @RequestParam(value = "memberId", required = true) String memberId,
                                                         @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        MemberAddressQuery memberAddressQuery = new MemberAddressQuery();
        memberAddressQuery.setId(id);
        memberAddressQuery.setExcludeIds(excludeIds);
        memberAddressQuery.setIds(ids);
        memberAddressQuery.setCdMemberId(memberId);
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
