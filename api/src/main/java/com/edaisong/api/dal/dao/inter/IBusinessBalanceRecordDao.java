package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.BusinessBalanceRecord;

public interface IBusinessBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessBalanceRecord record);

    int insertSelective(BusinessBalanceRecord record);

    BusinessBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessBalanceRecord record);

    int updateByPrimaryKey(BusinessBalanceRecord record);
}