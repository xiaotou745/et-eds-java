package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessThirdRelation;

public interface IBusinessThirdRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessThirdRelation record);

    int insertSelective(BusinessThirdRelation record);

    BusinessThirdRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessThirdRelation record);

    int updateByPrimaryKey(BusinessThirdRelation record);
}