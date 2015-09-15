package com.edaisong.api.dao.inter;

import java.util.List;
import java.util.Map;

import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.*;
import com.edaisong.entity.req.*;

public interface IClienterBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalanceRecord record);

    int insertSelective(ClienterBalanceRecord record);

    ClienterBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterBalanceRecord record);

    int updateByPrimaryKey(ClienterBalanceRecord record);
    
    
    PagedResponse<ClienterBalanceRecord> query(PagedClienterBalanceRecordReq req) ;
    
  /**
   * 根据订单获取对象
   * @author CaoHeYang
   * @param id
   * @date 20150831
   * @return
   */
     ClienterBalanceRecord getByOrderId(long id);

 	/**
 	 * API 获取骑士月账单信息
 	 * 茹化肖
 	 * 2015年9月10日10:24:07
 	 * */
 	Map<String,AccountBillModel>  getAccountBillListC(AccountBillCReq par);
 	
 	/**
	 * API 获取骑士日账单信息
	 * 茹化肖
	 * 2015年9月10日10:24:07
	 * */
	List<AccountBillDayCModel>  getAccountBillListDayC(PagedAccountBillDayCReq par);
	
	/**
	 * API 获取商户账单详情
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	AccountBillDetailCModel getAccountBillDetailC(AccountBillDetailCReq par);
}