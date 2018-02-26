package com.weixin.service.impl.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.dao.IActionDAO;
import com.weixin.dao.IMemberDAO;
import com.weixin.dao.IRoleDAO;
import com.weixin.service.back.IMemberServiceBack;
import com.weixin.vo.Member;

@Service
public class MemberServiceImpl implements IMemberServiceBack{
	@Autowired
	private IMemberDAO memberDAO;
	@Autowired
	private IRoleDAO roleDAO;
	@Autowired
	private IActionDAO actionDAO;
	

	@Override
	public Member get(String mid) {
		// TODO Auto-generated method stub
				return memberDAO.findById(mid);
	}

	
	@Override
	public Map<String, Object> listAuthMyMember(String mid) {
		Map<String,Object> map = new HashMap<>();
		map.put("allRoles", roleDAO.findAllRoleFlag(mid));
		map.put("allActions", actionDAO.findAllActionFlag(mid));
		return map;
	}


	@Override
	@RequiresAuthentication
	public boolean editPassword(String mid, String oldPassword, String newPassword) {
		Member vo = memberDAO.findById(mid);
		if(vo==null) {
			return false;
		}
		if(oldPassword.equals(vo.getPassword())) {
			Map<String,Object> map = new HashMap<>();
			map.put("mid", mid);
			map.put("newPassword", newPassword);
			return memberDAO.doUpdatePassword(map);
		}
		return false;
	}


	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:list")
	public List<Member> list() {
		return memberDAO.findAll();
	}

}
