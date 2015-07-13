package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessExpressRelation;

public interface IBusinessExpressRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessExpressRelation record);

    int insertSelective(BusinessExpressRelation record);

    BusinessExpressRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessExpressRelation record);

    int updateByPrimaryKey(BusinessExpressRelation record);
}