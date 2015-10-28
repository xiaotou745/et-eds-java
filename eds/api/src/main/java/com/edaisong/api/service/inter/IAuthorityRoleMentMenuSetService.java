package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.AuthorityRoleMentMenuSet;

public interface IAuthorityRoleMentMenuSetService {
	/**
	 * 修改给定角色的权限列表
	 * @author hailongzhao
	 * @date 20150902
	 * @return
	 */
	public boolean modifyAuthList(List<AuthorityRoleMentMenuSet> authList) ;
}
