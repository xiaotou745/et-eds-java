package com.edaisong.api.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;

@Repository
public class BusinessBalanceRecordDao extends DaoBase implements IBusinessBalanceRecordDao {
	@Override
	public int insert(BusinessBalanceRecord record) {
		int result = getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.insert",
				record);
		return result;
	}

	/**
	 * 
	 * 获取交易详情列表 2015年8月4日10:37:12 茹化肖
	 * 
	 * */
	@Override
	public PagedResponse<BusinessBalanceRecord> getTransDetailList(PagedTransDetailReq par) {
		PagedResponse<BusinessBalanceRecord> resp = new PagedResponse<BusinessBalanceRecord>();
		resp = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.getTransDetailList", par);
		return resp;
	}

	@Override
	public PagedResponse<BusinessBalanceRecord> customerGetTransDetailList(PagedCustomerSearchReq par) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.customerGetTransDetailList", par);
	}

	@Override
	public double queryBusinessRechargeTotalAmount(BussinessBalanceQueryReq par) throws ParseException {
		//Map<String, Object> paramsMap = new HashMap<String, Object>();
		//paramsMap.put("where", getBussinessBalanceQueryWhere(par));
		if (!StringUtils.isEmpty(par.getEndDate())) {
			Date finalDt = ParseHelper.ToDate(par.getEndDate(), "yyyy-MM-dd");
			finalDt = ParseHelper.plusDate(finalDt, 2, 1);
			par.setEndDate(ParseHelper.ToDateString(finalDt, "yyyy-MM-dd"));
		}
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.queryBusinessRechargeTotalAmount", par);
	}

	/**
	 * 导出商家收支记录数据
	 * @author pengyi
	 * @date 20150902
	 * @param par
	 * @return
	 */
	@Override
	public List<BusinessBalanceRecordModel> getBusinessBalanceRecordListForExport(PagedTransDetailReq par) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.getBusinessBalanceRecordListForExport", par);
	}
}
