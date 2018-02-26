package com.weixin.dao;

import java.util.List;
import java.util.Map;

import com.weixin.vo.Member;

public interface IMemberDAO {
	
	/**
	 * 查询全部用户数据
	 * @return
	 */
	public List<Member> findAll();
	
	/**
	 * 根据id实现member数据的查询
	 */
	public Member findById(String mid);
	
	/**
	 * 进行密码修改处理，此时要传递两个参数
	 * 1.key=mid value=用户编号
	 * 2.key=newPassword value=新密码
	 * @param param
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean doUpdatePassword(Map<String,Object> param);
}
