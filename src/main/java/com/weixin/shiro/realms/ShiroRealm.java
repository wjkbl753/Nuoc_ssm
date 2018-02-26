package com.weixin.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm{


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		String username = upToken.getUsername();
		Object credentials = null;
		
		if("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在");
		}
		if("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		if("admin".equals(username)) {
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}
		if("user".equals(username)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		ByteSource bytes = ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials,bytes,getName());
		return info;
	}

	public static void main(String[] args) {
		SimpleHash result = new SimpleHash("MD5", "123456", null, 1024);
		ByteSource bytes = ByteSource.Util.bytes("user");
		result = new SimpleHash("Md5", "123456", bytes, 1024);
		System.out.println(result);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(primaryPrincipal)) {
			roles.add("admin");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}
}
