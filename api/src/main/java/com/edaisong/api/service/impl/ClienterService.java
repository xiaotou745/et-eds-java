package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.entity.Account;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.resp.AccountResp;




@Service
public class ClienterService implements IClienterService {

	@Autowired
	private IClienterDao clienterDao;	
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;

	@Override
	public int modifyStatusById(Clienter record) 
	{
		return clienterDao.updateByPrimaryKeySelective(record);
	}

	
	public int modifyMoneyById(ClienterOptionReq record)
	{
		return clienterDao.updateMoneyById(record);
	}   	

	
	@Override
	public PagedResponse<ClienterModel> query(ClienterReq req) {
		return  clienterDao.query(req);
	}
	

	@Override
	public PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req) {
		return clienterDao.getBusinessClienters(req);
	}


	/**
	 *  更新骑士余额、可提现余额     
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	@Override
	public void updateCAccountBalance(ClienterMoney clienterMoney) {
		clienterDao.updateForWithdraw(clienterMoney.getAmount(),
				clienterMoney.getClienterId());// 更新商户余额
		//插入骑士余额流水
		ClienterBalanceRecord clienterBalanceRecord = new ClienterBalanceRecord();
		clienterBalanceRecord.setClienterid(clienterMoney.getClienterId());// 商户Id
		clienterBalanceRecord.setAmount(clienterMoney.getAmount());
		clienterBalanceRecord.setStatus((short)clienterMoney.getStatus()); // 流水状态
		clienterBalanceRecord.setRecordtype((short) clienterMoney.getRecordType()); // 
		clienterBalanceRecord.setOperator(clienterMoney.getOperator()); // 商家id
		clienterBalanceRecord.setWithwardid((long) clienterMoney.getWithwardId()); // 关联单id
		clienterBalanceRecord.setRelationno(clienterMoney.getRelationNo()); // 关联单号
		clienterBalanceRecord.setRemark(clienterMoney.getRemark()); // 注释
		clienterBalanceRecordDao.insert(clienterBalanceRecord);
		
	}
}
