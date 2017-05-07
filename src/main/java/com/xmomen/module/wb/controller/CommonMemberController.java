package com.xmomen.module.wb.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public String register(@RequestBody @Valid PcMember createPcMember) {
		CreateMember createMember = new CreateMember();
		createMember.setPhoneNumber(createPcMember.getPhoneNumber());
		createMember.setPassword(createPcMember.getPassword());
		createMember.setMemberAddressList(new ArrayList<MemberAddressCreate>());
		createMember.setEmail(createPcMember.getEmail());
		try {
			memberService.createMember(createMember);
		} catch (Exception e) {
			return "error";
		}
		return "login";
	}
	
	@RequestMapping(value = "/member/register", method = RequestMethod.GET)
	public String registerTest() {
		CreateMember createMember = new CreateMember();
		createMember.setPhoneNumber("15821503618");
		createMember.setPassword("123456");
		createMember.setMemberAddressList(new ArrayList<MemberAddressCreate>());
		createMember.setEmail("153643838@qq.com");
		try {
			memberService.createMember(createMember);
		} catch (Exception e) {
			return "error";
		}
		return "login";
	}
}
