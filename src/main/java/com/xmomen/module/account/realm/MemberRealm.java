package com.xmomen.module.account.realm;

import java.util.Set;
import java.util.TreeSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberService;

public class MemberRealm extends AuthorizingRealm {

	MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles = new TreeSet<String>();
        //用户默认有member的权限
        roles.add("user");
        roles.add("member");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(new TreeSet<String>());
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String phoneNumber = (String)token.getPrincipal();

		CdMember query = new CdMember();
		query.setPhoneNumber(phoneNumber);
        CdMember member = memberService.findMember(query);

        if(member == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                phoneNumber, //用户名
                member.getPassword(), //密码
                ByteSource.Util.bytes(AppConstants.PC_PASSWORD_SALT),//salt=phoneNumber
                getName()  //realm name
        );
        return authenticationInfo;
	}
	
	public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
