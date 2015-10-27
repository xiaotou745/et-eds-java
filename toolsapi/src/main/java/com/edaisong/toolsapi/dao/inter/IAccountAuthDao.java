package com.edaisong.toolsapi.dao.inter;

import java.util.List; 

import com.edaisong.toolsentity.AccountAuthority;
public interface IAccountAuthDao {
	List<Integer> getMenuIdsByAccountId(Integer id);
	/**
	 * 修改给定用户的权限列表
	 * @author hailongzhao
	 * @date 20150901
	 * @return
	 */
	public boolean modifyAuthList(List<AccountAuthority> authList) ;
}