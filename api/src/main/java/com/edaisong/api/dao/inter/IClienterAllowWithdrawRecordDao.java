package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterAllowWithdrawRecord;

public interface IClienterAllowWithdrawRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterAllowWithdrawRecord record);

    int insertSelective(ClienterAllowWithdrawRecord record);

    ClienterAllowWithdrawRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterAllowWithdrawRecord record);

    int updateByPrimaryKey(ClienterAllowWithdrawRecord record);
}