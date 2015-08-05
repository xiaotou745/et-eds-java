package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessExpressRelation;

public interface IBusinessExpressRelationDao {
    List<BusinessExpressRelation> selectByBusinessID(Integer businessID);
}