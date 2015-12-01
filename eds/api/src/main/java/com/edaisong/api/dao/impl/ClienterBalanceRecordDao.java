package com.edaisong.api.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.*;
import com.edaisong.entity.req.*;


@Repository
public class ClienterBalanceRecordDao extends DaoBase implements IClienterBalanceRecordDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccountBillDayCResultModel getAccountInMoneyAndOutMoney(PagedAccountBillDayCReq par) {
		return getMasterSqlSessionUtil().selectOne("IClienterBalanceRecordDao.getAccountInMoneyAndOutMoney",
				par);
	}
	/**
	 * 
	 */
	@Override
	public int insert(ClienterBalanceRecord record) {
		int result = getMasterSqlSessionUtil().insert("IClienterBalanceRecordDao.insert",
				record);
		return result;
	}

	@Override
	public int insertSelective(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterBalanceRecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public PagedResponse<ClienterBalanceRecord> query(PagedClienterBalanceRecordReq req) {

		PagedResponse<ClienterBalanceRecord> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IClienterBalanceRecordDao.query",
						req);
		return model;
	}

	  /**
	   * 根据订单获取对象
	   * 引处使用了 nolock
	   * @author CaoHeYang
	   * @param id
	   * @date 20150831
	   * @return
	   */
	@Override
	public ClienterBalanceRecord getByOrderId(long id) {
		Map< String, Object> map=new HashedMap();
		map.put("id", id);
		return getMasterSqlSessionUtil().selectOne("IClienterBalanceRecordDao.getByOrderId", map);
	}
	/***
	 * 骑士获取月账单信息
	 * 茹化肖
	 * 2015年9月14日11:14:17
	 */
	@Override
	public Map<String, AccountBillModel> getAccountBillListC(AccountBillCReq par) {
		String startDate=par.getMonthInfo()+"-01 00:00:00";
		String year=par.getMonthInfo().split("-")[0];//年
		String month=par.getMonthInfo().split("-")[1];//月
		String endDate="";
		if(month.equals("12"))
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
		params.put("clienterId", par.getClienterId());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<AccountBillModel> list= getReadOnlySqlSessionUtil().selectList("IClienterBalanceRecordDao.getAccountBillCList", params);
		Map<String,AccountBillModel> map=new HashMap<String,AccountBillModel>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDayInfo(), list.get(i));
		}
		return map;
	}
	/***
	 * 骑士获取日账单分页列表
	 * 茹化肖
	 * 2015年9月14日11:14:31
	 * 
	 */
	@Override
	public List<AccountBillDayCModel> getAccountBillListDayC(
			PagedAccountBillDayCReq par) {
		 List<AccountBillDayCModel> list=new ArrayList<AccountBillDayCModel>();
			PagedResponse<AccountBillDayCModel> result =getReadOnlySqlSessionUtil().selectPageList("IClienterBalanceRecordDao.getAccountBillDayCList", par);
			if(result.getResultList()!=null&&result.getResultList().size()>0)
			{
				list=result.getResultList();
			}
			return list;
	}
	/**
	 * 获取骑士账单详情
	 * 茹化肖
	 * 2015年9月14日12:57:14
	 */
	@Override
	public AccountBillDetailCModel getAccountBillDetailC(
			AccountBillDetailCReq par) {
		AccountBillDetailCModel model=getReadOnlySqlSessionUtil().selectOne("IClienterBalanceRecordDao.getAccountBillDetailC", par);
		return model;
	}

}
