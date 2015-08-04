package com.edaisong.api.service.inter;


import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.ClienterBalanceRecordReq;


public interface IClienterBalanceRecordService {
	PagedResponse<ClienterBalanceRecord>  query(ClienterBalanceRecordReq req);
}
