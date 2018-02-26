package com.weixin.handler.back;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.service.back.IMemberServiceBack;

@Controller
@RequestMapping("/member")
public class MemberHandler {
	@Autowired
	private IMemberServiceBack memberServiceBack;
	
	/**
	 * 密码修改前页面展示
	 * @return
	 */
	@RequestMapping("/editPasswordPre")
	public String editPasswordPre() {
		return "back/member/member_password";
	}
	
	/**
	 * 密码修改操作
	 * @param oldpassword
	 * @param newpassword
	 * @param map
	 * @return
	 */
	@RequestMapping("/editPassword")
	@RequiresAuthentication
	public String editPassword(String oldpassword,String newpassword,Map<String,Object> map) {
		//得到用户名mid：
		String mid = SecurityUtils.getSubject().getPrincipal().toString();
		
		//密码加盐转换：
		String oldPassword = new SimpleHash("MD5",oldpassword, ByteSource.Util.bytes(oldpassword), 1024).toString();
		String newPassword = new SimpleHash("MD5",newpassword, ByteSource.Util.bytes(newpassword), 1024).toString();
		if(memberServiceBack.editPassword(mid, oldPassword, newPassword)) {
			//修改成功：
			map.put("message", "密码修改成功");
		}else {
			//修改失败：
			map.put("message", "密码修改失败");
		}
		//退出登录：
		SecurityUtils.getSubject().logout();
		map.put("url", "/index");
		return "back/back_forward";
	}
	
	/**
	 * 查询所有member
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresRoles("member")
	@RequiresPermissions("member:list")
	public String list(Map<String,Object> map) {
		map.put("allMembers", memberServiceBack.list());
		return "back/member/member_list";
	}
	
	
}
