package com.weixin.handler;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weixin.util.ResourcesUtil;

/**
 * 包括登录。路径
 * @author woyuno
 *
 */
@Controller
public class ShiroHandler {

	@RequestMapping("/loginUrl")
	public String loginUrl() {
		return ResourcesUtil.getValue("Pages", "shiro.loginUrl.page");
	}
	
	@RequestMapping("/successUrl")
	public String successUrl() {
		return ResourcesUtil.getValue("Pages", "shiro.successUrl.page");
	}
	
	@RequestMapping("/unauthorizedUrl")
	public String unauthorizedUrl() {
		return ResourcesUtil.getValue("Pages", "shiro.unauthorizedUrl.page");
	}
	
	@RequestMapping("/login")
	public String login(String mid,String password,String code,HttpSession httpSession) {
		String rand = (String)httpSession.getAttribute("rand");
		//判断验证码：
		if(!rand.equals(code)) {
			return ResourcesUtil.getValue("Pages", "shiro.loginUrl.page");
		}
		 Subject currentUser = SecurityUtils.getSubject();
	        if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken(mid,password);
	            token.setRememberMe(true);
	            try {
	                currentUser.login(token);
	            }catch (AuthenticationException ae) {
	            	System.out.println("登录失败"+ae.getMessage());
	            }
	        }
		return "redirect:/successUrl";
	}
	
	
	
}
