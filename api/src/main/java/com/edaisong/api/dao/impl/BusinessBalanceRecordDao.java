package com.edaisong.api.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
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
import com.edaisong.entity.domain.AccountBillDayModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.PagedAccountBillDayReq;
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
	/**
	 * API 获取商户月账单信息
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	@Override
	public Map<String,AccountBillModel>  getAccountBillListB(AccountBillBReq par) {
		String startDate=par.getMonthInfo()+"-01 00:00:00";
		String year=par.getMonthInfo().split("-")[0];//年
		String month=par.getMonthInfo().split("-")[1];//月
		String endDate="";
		if(month=="12")
		{
			year=String.valueOf(Integer.parseInt(year, 10)+1);//十二月将年+1
			endDate=year+"-01-01 00:00:00";//结束时间为 2016-01-01 00:00:00
		}
		else {
			//结束时间为下个月一号之前
			month=String.valueOf(Integer.parseInt(month,10)+1);//将月份+1
			endDate=year+"-"+month+"-01 00:00:00";//结束时间为 2015-12-01 00:00:00
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", par.getBusinessId());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<AccountBillModel> list= getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.getAccountBillBList", params);
		Map<String,AccountBillModel> map=new HashMap<String,AccountBillModel>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDayInfo(), list.get(i));
		}
		return map;
	}
	/**
	 * api 获取商户日账单 
	 * 茹化肖
	 * 2015年9月11日11:10:26
	 */
	@Override
	public List<AccountBillDayModel> getAccountBillListDayB(
			PagedAccountBillDayReq par) {
		 List<AccountBillDayModel> list=new ArrayList<AccountBillDayModel>();
		PagedResponse<AccountBillDayModel> result =getReadOnlySqlSessionUtil().selectPageList("getAccountBillDayBList", par);
		if(result.getResultList()!=null&&result.getResultList().size()>0)
		{
			list=result.getResultList();
		}
		return list;
	}
}
