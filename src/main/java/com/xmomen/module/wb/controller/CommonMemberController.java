package com.xmomen.module.wb.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.wb.model.PcMember;

@RestController
public class CommonMemberController {

	@Autowired
	MemberService memberService;
	
	/**
	 * 普通用户注册
	 */
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	public CdMember register(@RequestBody @Valid PcMember createPcMember) {
		CreateMember createMember = new CreateMember();
		createMember.setPhoneNumber(createPcMember.getPhoneNumber());
		createMember.setPassword(createPcMember.getPassword());
		createMember.setMemberAddressList(new ArrayList<MemberAddressCreate>());
		createMember.setEmail(createPcMember.getEmail());
		
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(createPcMember.getPhoneNumber());
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember == null) {
			memberService.createMember(createMember);
		} else if(StringUtils.isEmpty(cdMember.getPassword())) {
			memberService.updatePassword(cdMember.getId(), createPcMember.getPassword(), "");
		} else {
			throw new IllegalArgumentException("用户已被注册");
		}
		cdMember.setPassword("");
		return cdMember;
	}
}
