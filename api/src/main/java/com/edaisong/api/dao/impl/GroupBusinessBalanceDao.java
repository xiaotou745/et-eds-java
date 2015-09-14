package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessBalanceDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBalanceRecord;
import com.edaisong.entity.req.PagedGroupBussinessBalanceReq;

@Repository
public class GroupBusinessBalanceDao extends DaoBase implements IGroupBusinessBalanceDao{

	@Override
	public PagedResponse<GroupBusinessBalanceRecord> getGroupBusinessRecord(
			PagedGroupBussinessBalanceReq req) { 
		return getReadOnlySqlSessionUtil().selectPageList("com.edaisong.api.dao.inter.IGroupBusinessBalanceDao.getGroupBusinessRecord", req);
	}

	@Override
	public List<GroupBusinessBalanceRecord> getGroupBusinessBalanceRecordForExport(
			PagedGroupBussinessBalanceReq req) {
		return getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.IGroupBusinessBalanceDao.getGroupBusinessRecordForExport", req);
	} 
}
