package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AuthorityRoleMentMenuSet;

public interface IAuthorityRoleMentMenuSetDao {
	/**
	 * 修改给定角色的权限列表
	 * @author hailongzhao
	 * @date 20150902
	 * @return
	 */
	public boolean modifyAuthList(List<AuthorityRoleMentMenuSet> authList) ;
}