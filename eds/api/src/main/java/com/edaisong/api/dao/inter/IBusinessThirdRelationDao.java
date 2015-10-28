package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessThirdRelation;
import com.edaisong.entity.domain.BusinessThirdRelationModel;

public interface IBusinessThirdRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessThirdRelation record);

    int insertSelective(BusinessThirdRelation record);

    BusinessThirdRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessThirdRelation record);

    int updateByPrimaryKey(BusinessThirdRelation record);
    List<BusinessThirdRelationModel> getListByBusinessID(int businessID);
}