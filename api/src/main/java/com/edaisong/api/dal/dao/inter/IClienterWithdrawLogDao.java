package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ClienterWithdrawLog;

public interface IClienterWithdrawLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterWithdrawLog record);

    int insertSelective(ClienterWithdrawLog record);

    ClienterWithdrawLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterWithdrawLog record);

    int updateByPrimaryKey(ClienterWithdrawLog record);
}