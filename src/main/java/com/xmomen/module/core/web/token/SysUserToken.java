package com.xmomen.module.core.web.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class SysUserToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private final String realmKey = "UserRealm";
	
	public SysUserToken(final String username, final String password,
            final boolean rememberMe, final String host) {
		super(username, password != null ? password.toCharArray() : null, rememberMe, host);
	}
	
	public String getRealmKey() {
		return this.realmKey;
	}
}
