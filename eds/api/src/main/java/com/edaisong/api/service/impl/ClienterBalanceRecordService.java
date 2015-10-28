package com.edaisong.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IClienterBalanceRecordService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.Account;
import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AccountBillDayCModel;
import com.edaisong.entity.domain.AccountBillDayCResultModel;
import com.edaisong.entity.domain.AccountBillDayModel;
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillDetailCModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountBillCReq;
import com.edaisong.entity.req.AccountBillDetailCReq;
import com.edaisong.entity.req.PagedAccountBillDayCReq;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.PagedClienterReq;

@Service
public class ClienterBalanceRecordService implements IClienterBalanceRecordService{

	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;


	@Override
	public  PagedResponse<ClienterBalanceRecord>  query(PagedClienterBalanceRecordReq req)
	{
		return  clienterBalanceRecordDao.query(req);
	}	
	
	/**
	 * C端 获取月账单信息
	 * 茹化肖
	 * 2015年9月14日09:51:19
	 */
	@Override
	public AccountBillResultModel getAccountBillListC(AccountBillCReq par) {
		List<AccountBillModel> list = new ArrayList<AccountBillModel>();
		Map<String, AccountBillModel> map=clienterBalanceRecordDao.getAccountBillListC(par);
		String year = par.getMonthInfo().split("-")[0];// 年
		String month = par.getMonthInfo().split("-")[1];// 月
		Integer dayscount = ParseHelper.GetMixDay(year, month);// 共多少天
		int nowmonth=ParseHelper.GetInDate(new Date(), 2);//当前月份
		if(nowmonth==Integer.parseInt(month))//查询月份等于当前月份
		{
			//重置当月天数
			dayscount=ParseHelper.GetInDate(new Date(), 3);
		}
		Double monthOutMoney=0.0;//月总支出
		Double monthInMoney=0.0;//月总收入
		
		for (int i = 1; i <= dayscount; i++) {//创建当月的每天的数据
			String dayInfo="";
			if(i<10)
			{
				dayInfo=par.getMonthInfo()+"-0"+i;
			}
			else {
				dayInfo= par.getMonthInfo()+"-"+String.valueOf(i);
			}
			//初始化数据
			AccountBillModel model=new AccountBillModel();
			model.setDayInfo(dayInfo);
			model.setInMoney(0.0);
			model.setHasDatas(0);
			model.setOutMoney(0.0);
			if(map.containsKey(dayInfo))
			{
				//map中存在这天的数据 覆盖到list
				model.setInMoney(map.get(dayInfo).getInMoney());
				model.setHasDatas(1);
				model.setOutMoney(map.get(dayInfo).getOutMoney());
				monthInMoney+=map.get(dayInfo).getInMoney();//累计当月收入
				monthOutMoney+=map.get(dayInfo).getOutMoney();//累计当月支出
			}
			list.add(model);
		}
		//返回对象数据
		AccountBillResultModel resultModel=new AccountBillResultModel();
		resultModel.setInMoney(monthInMoney);
		resultModel.setOutMoney(monthOutMoney);
		resultModel.setListDays(list);
		return resultModel;
	}
	/***
	 * 骑士获取日账单分页列表
	 * 
	 */
	@Override
	public AccountBillDayCResultModel getAccountBillListDayC(
			PagedAccountBillDayCReq par) {
		String daystr=par.getDayInfo();
		par.setStratDate(daystr+" 00:00:00");//设置开始时间
		par.setEndDate(daystr+" 23:59:59");//设置结束时间
		List<AccountBillDayCModel> list=clienterBalanceRecordDao.getAccountBillListDayC(par);
//		double outmoney=0;
//		double inmoney=0;
//		for (AccountBillDayCModel accountBillDayModel : list) {
//			if(accountBillDayModel.getAmount()>0)
//			{
//				inmoney+=accountBillDayModel.getAmount();
//			}
//			else {
//				outmoney+=accountBillDayModel.getAmount();
//			}
//		}
		
		AccountBillDayCResultModel billDayModel= clienterBalanceRecordDao.getAccountInMoneyAndOutMoney(par);
		
		AccountBillDayCResultModel resultModel=new AccountBillDayCResultModel();
		resultModel.setInMoney(billDayModel==null?0:billDayModel.getInMoney());
		resultModel.setOutMoney(billDayModel==null?0:billDayModel.getOutMoney());
		resultModel.setListRecordS((ArrayList<AccountBillDayCModel>)list);
		return resultModel;
	}
	/***
	 * 获取骑士账单详情
	 * 茹化肖
	 * 2015年9月14日12:53:29
	 */
	@Override
	public AccountBillDetailCModel getAccountBillDetailC(
			AccountBillDetailCReq par) {
		return clienterBalanceRecordDao.getAccountBillDetailC(par);
	}

}
