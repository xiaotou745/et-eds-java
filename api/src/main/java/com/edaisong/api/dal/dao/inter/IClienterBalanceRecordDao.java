package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.req.ClienterBalanceRecordReq;

public interface IClienterBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalanceRecord record);

    int insertSelective(ClienterBalanceRecord record);

    ClienterBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterBalanceRecord record);

    int updateByPrimaryKey(ClienterBalanceRecord record);
    
    
    ResponsePageList<ClienterBalanceRecord> query(ClienterBalanceRecordReq req) ;
}