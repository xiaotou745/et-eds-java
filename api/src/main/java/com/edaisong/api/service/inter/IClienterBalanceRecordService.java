package com.edaisong.api.service.inter;


import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.req.ClienterBalanceRecordReq;


public interface IClienterBalanceRecordService {
	  ResponsePageList<ClienterBalanceRecord>  query(ClienterBalanceRecordReq req);
}
