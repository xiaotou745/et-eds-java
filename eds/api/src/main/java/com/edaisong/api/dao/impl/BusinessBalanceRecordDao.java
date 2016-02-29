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
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillDetailModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.AccountBillCReq;
import com.edaisong.entity.req.AccountBillDetailReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.PagedAccountBillDayReq;
import com.edaisong.entity.req.PagedBusinessBalanceRecordReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;

@Repository
public class BusinessBalanceRecordDao extends DaoBase implements IBusinessBalanceRecordDao {
	@Override
	public int insert(BusinessBalanceRecord record) {
		int result = getMasterSqlSessionUtil().insert("IBusinessBalanceRecordDao.insert",
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
				"IBusinessBalanceRecordDao.getTransDetailList", par);
		return resp;
	}

	/**
	 * 获取商家收入支出
	 * 2015年10月21日 20:40:39
	 * 窦海超 
	 * */
	@Override
	public AccountBillDayResultModel getAccountInMoneyAndOutMoney(
			PagedAccountBillDayReq par) {
		return getMasterSqlSessionUtil().selectOne("IBusinessBalanceRecordDao.getAccountInMoneyAndOutMoney",
				par);
	}

	@Override
	public PagedResponse<BusinessBalanceRecord> customerGetTransDetailList(PagedCustomerSearchReq par) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"IBusinessBalanceRecordDao.customerGetTransDetailList", par);
	}

	@Override
	public double queryBusinessRechargeTotalAmount(BussinessBalanceQueryReq par) throws ParseException {
		return getReadOnlySqlSessionUtil().selectOne(
				"IBusinessBalanceRecordDao.queryBusinessRechargeTotalAmount", par);
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
				"IBusinessBalanceRecordDao.getBusinessBalanceRecordListForExport", par);
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
		String endDate=ParseHelper.ToDateString(ParseHelper.plusDate(ParseHelper.ToDate(startDate), 1, 1));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", par.getBusinessId());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<AccountBillModel> list= getReadOnlySqlSessionUtil().selectList("IBusinessBalanceRecordDao.getAccountBillBList", params);
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
		PagedResponse<AccountBillDayModel> result =getReadOnlySqlSessionUtil().selectPageList("IBusinessBalanceRecordDao.getAccountBillDayBList", par);
		if(result.getResultList()!=null&&result.getResultList().size()>0)
		{
			list=result.getResultList();
		}
		return list;
	}
	/**
	 * API 获取商户账单详情
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	@Override
	public AccountBillDetailModel getAccountBillDetailB(AccountBillDetailReq par) {
		AccountBillDetailModel model=getReadOnlySqlSessionUtil().selectOne("IBusinessBalanceRecordDao.getAccountBillDetailB", par);
		return model;
	}

	@Override
	public int groupInsert(BusinessBalanceRecord record) {
		int result = getMasterSqlSessionUtil().insert("IBusinessBalanceRecordDao.groupInsert",
				record);
		return result;
	}

	
	/**
	 * 集团中心商户流水列表页面
	 * 
	 * @author 胡灵波
	 * @Date 2016年2月26日14:23:26
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<BusinessBalanceRecordModel> getGroupBalanceRecord(PagedBusinessBalanceRecordReq search) {

		PagedResponse<BusinessBalanceRecordModel> result = new PagedResponse<BusinessBalanceRecordModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"IBusinessBalanceRecordDao.getGroupBalanceRecord", search);
		return result;
	}

}
