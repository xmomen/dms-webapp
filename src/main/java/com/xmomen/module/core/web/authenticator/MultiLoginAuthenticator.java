package com.xmomen.module.core.web.authenticator;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import com.xmomen.module.core.web.token.MemberUserToken;
import com.xmomen.module.core.web.token.SysUserToken;

public class MultiLoginAuthenticator extends ModularRealmAuthenticator {

	
	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		assertRealmsConfigured();
		//根据不同类型的token找对应的的Realm
        String realmKey = "";
        if(authenticationToken instanceof MemberUserToken) {
            realmKey = ((MemberUserToken)authenticationToken).getRealmKey();
        } else if(authenticationToken instanceof SysUserToken) {
        	realmKey = ((SysUserToken)authenticationToken).getRealmKey();
        }
        if(StringUtils.isEmpty(realmKey)) {
        	// 抛异常还是支持multiple Realms
        	// return doMultiRealmAuthentication(realms, authenticationToken);
        	throw new AuthenticationException("不支持token:" + authenticationToken.getClass().getName());
        } else {
            Realm realm = lookupRealm(realmKey);
            return doSingleRealmAuthentication(realm, authenticationToken);
        }
	}

	protected Realm lookupRealm(String realmName) throws AuthenticationException {
		Collection<Realm> realms = getRealms();
		for(Realm realm: realms) {
			// 不是很严格，但是根据是否名字包含就足够
			if(realm.getName().indexOf(realmName) > -1) {
				return realm;
			}
		}
		throw new AuthenticationException("找不到对应的的Realm:" + realmName);
	}
}
