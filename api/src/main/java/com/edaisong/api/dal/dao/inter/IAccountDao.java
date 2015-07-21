package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.Account;

public interface IAccountDao {
	
	List<Account> query();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}