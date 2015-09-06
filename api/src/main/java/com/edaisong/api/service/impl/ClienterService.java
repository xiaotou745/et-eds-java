package com.edaisong.api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterAllowWithdrawRecordDao;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterAllowWithdrawRecord;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;

@Service
public class ClienterService implements IClienterService {

	@Autowired
	private IClienterDao clienterDao;	
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
@Autowired
private IClienterAllowWithdrawRecordDao clienterAllowWithdrawRecordDao;
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
	public PagedResponse<ClienterModel> query(PagedClienterReq req) {
		return  clienterDao.query(req);
	}
	

	@Override
	public PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req) {
		return clienterDao.getBusinessClienters(req);
	}


	/**
	 * 更新骑士余额
	 * 
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	@Override
	public void updateCAccountBalance(ClienterMoney clienterMoney) {
		clienterDao.updateCAccountBalance(clienterMoney.getAmount(),
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


	/**
	 * 更新骑士可提现余额
	 * 
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	@Override
	public void updateCAllowWithdrawPrice(ClienterMoney clienterMoney) {
		clienterDao.updateCAllowWithdrawPrice(clienterMoney.getAmount(),
				clienterMoney.getClienterId());// 更新商户余额
		//插入骑士余额可提现流水
		ClienterAllowWithdrawRecord clienterAllowWithdrawRecord = new ClienterAllowWithdrawRecord();
		clienterAllowWithdrawRecord.setClienterid(clienterMoney.getClienterId());// 商户Id
		clienterAllowWithdrawRecord.setAmount(clienterMoney.getAmount());
		clienterAllowWithdrawRecord.setStatus((short)clienterMoney.getStatus()); // 流水状态
		clienterAllowWithdrawRecord.setRecordtype((short) clienterMoney.getRecordType()); // 
		clienterAllowWithdrawRecord.setOperator(clienterMoney.getOperator()); // 商家id
		clienterAllowWithdrawRecord.setWithwardid((long) clienterMoney.getWithwardId()); // 关联单id
		clienterAllowWithdrawRecord.setRelationno(clienterMoney.getRelationNo()); // 关联单号
		clienterAllowWithdrawRecord.setRemark(clienterMoney.getRemark()); // 注释
		clienterAllowWithdrawRecordDao.insert(clienterAllowWithdrawRecord);
	}


	/**
	 * 更新骑士余额、可提现余额
	 * 
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	@Override
	public void updateCBalanceAndWithdraw(ClienterMoney clienterMoney) {
		clienterDao.updateCAccountBalance(clienterMoney.getAmount(),
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
		//插入骑士余额可提现流水
		ClienterAllowWithdrawRecord clienterAllowWithdrawRecord = new ClienterAllowWithdrawRecord();
		clienterAllowWithdrawRecord.setClienterid(clienterMoney.getClienterId());// 商户Id
		clienterAllowWithdrawRecord.setAmount(clienterMoney.getAmount());
		clienterAllowWithdrawRecord.setStatus((short)clienterMoney.getStatus()); // 流水状态
		clienterAllowWithdrawRecord.setRecordtype((short) clienterMoney.getRecordType()); // 
		clienterAllowWithdrawRecord.setOperator(clienterMoney.getOperator()); // 商家id
		clienterAllowWithdrawRecord.setWithwardid((long) clienterMoney.getWithwardId()); // 关联单id
		clienterAllowWithdrawRecord.setRelationno(clienterMoney.getRelationNo()); // 关联单号
		clienterAllowWithdrawRecord.setRemark(clienterMoney.getRemark()); // 注释
		clienterAllowWithdrawRecordDao.insert(clienterAllowWithdrawRecord);
	}

	/**
	 * 获得骑士列表
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<ClienterBindInfoModel> getClienterList(PagedClienterSearchReq req) {
		return clienterDao.getClienterBindInfoList(req);
	}
}
