package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessWithdrawLog;

public interface IBusinessWithdrawLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessWithdrawLog record);

    int insertSelective(BusinessWithdrawLog record);

    BusinessWithdrawLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessWithdrawLog record);

    int updateByPrimaryKey(BusinessWithdrawLog record);
}