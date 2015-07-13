package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.Account;

public interface IAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}