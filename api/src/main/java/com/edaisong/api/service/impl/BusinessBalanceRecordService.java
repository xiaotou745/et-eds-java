package com.edaisong.api.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
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
	@Override
	public double queryBusinessRechargeTotalAmount(BussinessBalanceQueryReq par) throws ParseException {
		return businessBalanceRecordDao.queryBusinessRechargeTotalAmount(par);
	}
	@Override
	public List<BusinessBalanceRecordModel> getBusinessBalanceRecordListForExport(PagedTransDetailReq par) {
		return businessBalanceRecordDao.getBusinessBalanceRecordListForExport(par);
	}

}
