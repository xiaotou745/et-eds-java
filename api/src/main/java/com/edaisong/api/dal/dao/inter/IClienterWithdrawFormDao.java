package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ClienterWithdrawForm;

public interface IClienterWithdrawFormDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterWithdrawForm record);

    int insertSelective(ClienterWithdrawForm record);

    ClienterWithdrawForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterWithdrawForm record);

    int updateByPrimaryKey(ClienterWithdrawForm record);
}