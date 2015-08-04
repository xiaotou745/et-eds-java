package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.req.TransDetailReq;

public interface IBusinessBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessBalanceRecord record);

    int insertSelective(BusinessBalanceRecord record);

    BusinessBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessBalanceRecord record);

    int updateByPrimaryKey(BusinessBalanceRecord record);
    List<BusinessBalanceRecord> getTransDetailList(TransDetailReq par);
}