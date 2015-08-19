package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.service.inter.IBusinessFinanceService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedTransDetailReq;
@Service
public class BusinessFinanceService implements IBusinessFinanceService {
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;
	@Override
	public PagedResponse<BusinessBalanceRecord> getTransDetailList(
			PagedTransDetailReq par) {
		return businessBalanceRecordDao.getTransDetailList(par);
	}

}
