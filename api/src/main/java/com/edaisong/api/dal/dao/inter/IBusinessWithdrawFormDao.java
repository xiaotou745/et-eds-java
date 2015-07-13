package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessWithdrawForm;

public interface IBusinessWithdrawFormDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessWithdrawForm record);

    int insertSelective(BusinessWithdrawForm record);

    BusinessWithdrawForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessWithdrawForm record);

    int updateByPrimaryKey(BusinessWithdrawForm record);
}