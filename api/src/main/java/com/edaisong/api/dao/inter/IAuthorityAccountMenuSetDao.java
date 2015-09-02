package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AuthorityAccountMenuSet;

public interface IAuthorityAccountMenuSetDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityAccountMenuSet record);

    int insertSelective(AuthorityAccountMenuSet record);

    AuthorityAccountMenuSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityAccountMenuSet record);

    int updateByPrimaryKey(AuthorityAccountMenuSet record);
    
    List<Integer> getMenuIdsByAccountId(Integer id);
	/**
	 * 修改给定用户的权限列表
	 * @author hailongzhao
	 * @date 20150901
	 * @return
	 */
	public boolean modifyAuthList(List<AuthorityAccountMenuSet> authList) ;
}