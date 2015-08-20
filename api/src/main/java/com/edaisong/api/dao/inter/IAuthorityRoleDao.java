package com.edaisong.api.dao.inter;

import com.edaisong.entity.AuthorityRole;

public interface IAuthorityRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityRole record);

    int insertSelective(AuthorityRole record);

    AuthorityRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityRole record);

    int updateByPrimaryKey(AuthorityRole record);
}