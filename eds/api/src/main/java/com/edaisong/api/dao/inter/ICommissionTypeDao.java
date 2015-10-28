package com.edaisong.api.dao.inter;

import com.edaisong.entity.CommissionType;

public interface ICommissionTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CommissionType record);

    int insertSelective(CommissionType record);

    CommissionType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommissionType record);

    int updateByPrimaryKey(CommissionType record);
}