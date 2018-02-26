package com.weixin.shiro.realms;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.weixin.service.back.IMemberServiceBack;
import com.weixin.vo.Member;

public class MemberRealm extends AuthorizingRealm{
	private Logger log = Logger.getLogger(MemberRealm.class);
	@Autowired
	private IMemberServiceBack memberServiceBack;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		log.info("********** 2、用户角色与权限：doGetAuthorizationInfo **********");
		String mid = (String) principal.getPrimaryPrincipal() ;	// 取得用户登录名
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo() ;	// 定义授权信息的返回数据
		try {
			Map<String,Object> map = this.memberServiceBack.listAuthMyMember(mid);
			Set<String> allRoles = (Set<String>) map.get("allRoles") ;
			Set<String> allActions = (Set<String>) map.get("allActions") ;
			auth.setRoles(allRoles);// 所有的角色必须以Set集合的形式出现
			auth.setStringPermissions(allActions); 	// 所有的权限必须以Set集合的形式出现
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return auth;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("doGetAuthenticationInfo:登录认证");
		//拿到用户名mid：
		String mid = (String)token.getPrincipal();
		Member vo = memberServiceBack.get(mid);
		if(vo==null) {
			throw new UnknownAccountException("没有此用户");
		}else {
			//拿到用户表单密码：
			 String password = new String((char[])token.getCredentials());
			//盐：
			ByteSource salt = ByteSource.Util.bytes(password);
			String password_salt = new SimpleHash("MD5", password, salt, 1024).toString();
			if(vo.getPassword().equals(password_salt)) {
				return new SimpleAuthenticationInfo(mid, password, "memberRealm");
			}else {
				throw new IncorrectCredentialsException("密码错误！");
			}
		}
	}
	
	public static void main(String[] args) {
		ByteSource bytes = ByteSource.Util.bytes("hello");
		SimpleHash result = new SimpleHash("MD5", "hello", bytes, 1024);
		System.out.println(result);
	}

}
