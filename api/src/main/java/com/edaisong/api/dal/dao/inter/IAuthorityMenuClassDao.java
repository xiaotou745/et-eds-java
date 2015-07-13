package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.AuthorityMenuClass;

public interface IAuthorityMenuClassDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityMenuClass record);

    int insertSelective(AuthorityMenuClass record);

    AuthorityMenuClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityMenuClass record);

    int updateByPrimaryKey(AuthorityMenuClass record);
}