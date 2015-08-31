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

	private String getBussinessBalanceQueryWhere(BussinessBalanceQueryReq par) throws ParseException {
		StringBuilder sbSqlWhere = new StringBuilder(" bbr.status=1  ");
		if (!StringUtils.isEmpty(par.getBusinessId())) {
			sbSqlWhere.append(" AND bbr.BusinessId='" + par.getBusinessId() + "' ");
		}
		if (!StringUtils.isEmpty(par.getStartDate())) {
			sbSqlWhere.append(" AND bbr.operatetime>='" + par.getStartDate() + "' ");
		}
		if (!StringUtils.isEmpty(par.getEndDate())) {
			Date finalDt = ParseHelper.ToDate(par.getEndDate(), "yyyy-MM-dd");
			finalDt = ParseHelper.plusDate(finalDt, 2, 1);
			sbSqlWhere.append(" AND bbr.operatetime<='" + ParseHelper.ToDateString(finalDt, "yyyy-MM-dd") + "' ");
		}
		if (!StringUtils.isEmpty(par.getName())) {
			sbSqlWhere.append(" AND Name='" + par.getName() + "' ");
		}
		if (!StringUtils.isEmpty(par.getPhoneNo())) {
			sbSqlWhere.append(" AND PhoneNo='" + par.getPhoneNo() + "' ");
		}
		if (!StringUtils.isEmpty(par.getCityId())) {
			sbSqlWhere.append(" AND CityId=" + par.getCityId() + " ");
		}
		if (par.getRechargePrice() > 0) {
			sbSqlWhere.append(" AND Amount>=" + par.getRechargePrice() + " ");
		}
		if (par.getRechargeType() > 0) {
			if (par.getRechargeType() == 3) {// 充值赠送
				sbSqlWhere.append(" and RecordType=12  ");
			}else if (par.getRechargeType() == 1) {// 系统充值
				sbSqlWhere.append("and  bbr.RecordType=9 AND Remark!='商家客户端充值'");
			} else if (par.getRechargeType() == 2) {// 客户端充值
				sbSqlWhere.append(" and bbr.RecordType=9 AND Remark='商家客户端充值'");
			}
		} else {
			sbSqlWhere.append("  and (bbr.RecordType=9 or RecordType=12) ");
		}
		return sbSqlWhere.toString();
	}
}
