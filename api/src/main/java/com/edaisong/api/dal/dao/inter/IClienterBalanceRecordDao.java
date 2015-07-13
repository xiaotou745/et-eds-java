package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.ClienterBalanceRecord;

public interface IClienterBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalanceRecord record);

    int insertSelective(ClienterBalanceRecord record);

    ClienterBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterBalanceRecord record);

    int updateByPrimaryKey(ClienterBalanceRecord record);
}