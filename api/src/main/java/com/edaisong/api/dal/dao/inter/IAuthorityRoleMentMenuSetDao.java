package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.AuthorityRoleMentMenuSet;

public interface IAuthorityRoleMentMenuSetDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityRoleMentMenuSet record);

    int insertSelective(AuthorityRoleMentMenuSet record);

    AuthorityRoleMentMenuSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityRoleMentMenuSet record);

    int updateByPrimaryKey(AuthorityRoleMentMenuSet record);
}