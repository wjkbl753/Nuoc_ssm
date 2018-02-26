package com.weixin.service.back;

import java.util.List;
import java.util.Map;

import com.weixin.vo.Member;

public interface IMemberServiceBack {
	
	/**
	 * 实现全部用户的列表显示
	 * @return 如果没有数据，集合长度==0
	 */
	public List<Member> list();
	
	/**
	 * 根据用户id查询出用户的完整数据
	 * @param mid
	 * @return
	 */
	public Member get(String mid);
	
	/**
	 * 根据用户mid查询用户的角色和权限
	 * @param mid
	 * @return
	 */
	public Map<String,Object> listAuthMyMember(String mid);
	
	/**
	 * 修改密码
	 * @param mid 要修改密码的当前用户
	 * @param oldPassword 原始密码
	 * @param newPassword 新密码
	 * @return 成功返回true，否则返回false
	 */
	public boolean editPassword(String mid,String oldPassword,String newPassword);
}
