package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBalanceRecord;
import com.edaisong.entity.req.PagedGroupBussinessBalanceReq;

public interface IGroupBusinessBalanceDao {
	PagedResponse<GroupBusinessBalanceRecord> getGroupBusinessRecord(
			PagedGroupBussinessBalanceReq req);

	List<GroupBusinessBalanceRecord> getGroupBusinessBalanceRecordForExport(
			PagedGroupBussinessBalanceReq req);
}
