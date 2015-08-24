package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;
@Service
public class BusinessBalanceRecordService implements IBusinessBalanceRecordService {
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;
	@Override
	public PagedResponse<BusinessBalanceRecord> getTransDetailList(
			PagedTransDetailReq par) {
		return businessBalanceRecordDao.getTransDetailList(par);
	}
	@Override
	public PagedResponse<BusinessBalanceRecord> customerGetTransDetailList(
			PagedCustomerSearchReq par) {
		return businessBalanceRecordDao.customerGetTransDetailList(par);
	}

}