package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.AuthorityAccountMenuSet;

public interface IAuthorityAccountMenuSetDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityAccountMenuSet record);

    int insertSelective(AuthorityAccountMenuSet record);

    AuthorityAccountMenuSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityAccountMenuSet record);

    int updateByPrimaryKey(AuthorityAccountMenuSet record);
}