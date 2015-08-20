package com.edaisong.api.dao.inter;

import com.edaisong.entity.AccountCityRelation;

public interface IAccountCityRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountCityRelation record);

    int insertSelective(AccountCityRelation record);

    AccountCityRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountCityRelation record);

    int updateByPrimaryKey(AccountCityRelation record);
}