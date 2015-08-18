package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessClienterRelation;

public interface IBusinessClienterRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessClienterRelation record);

    int insertSelective(BusinessClienterRelation record);

    BusinessClienterRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessClienterRelation record);

    int updateByPrimaryKey(BusinessClienterRelation record);
}