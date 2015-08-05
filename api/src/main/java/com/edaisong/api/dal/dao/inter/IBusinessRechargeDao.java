package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessRecharge;

public interface IBusinessRechargeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessRecharge record);

    int insertSelective(BusinessRecharge record);

    BusinessRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessRecharge record);

    int updateByPrimaryKey(BusinessRecharge record);
}