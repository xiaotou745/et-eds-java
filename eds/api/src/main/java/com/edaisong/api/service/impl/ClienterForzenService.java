package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.dao.inter.IClienterForzenDao;
import com.edaisong.api.dao.inter.IClienterForzenLogDao;
import com.edaisong.api.service.inter.IClienterForzenService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.core.enums.ClienterBalanceRecordRecordType;
import com.edaisong.core.enums.ClienterBalanceRecordStatus;
import com.edaisong.core.enums.ClienterForzenType;
import com.edaisong.entity.ClienterForzen;
import com.edaisong.entity.ClienterForzenLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.RecordType;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.req.ClienterForzenBalanceReq;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.ClienterUnfreezeReq;
import com.edaisong.entity.req.PagedClienterForzenReq;

@Service
public class ClienterForzenService implements IClienterForzenService {
	@Autowired
	private IClienterForzenDao clienterForzenDao;
	@Autowired
	private IClienterForzenLogDao clienterForzenLogDao;
	@Autowired
	private IClienterService clienterService;

	/*
	 * 获取骑士冻结单信息 茹化肖 2015年9月9日11:30:19
	 */
	@Override
	public PagedResponse<ClienterForzen> getForzenList(
			PagedClienterForzenReq par) {
		// if()
		return clienterForzenDao.getForzenList(par);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public ResponseBase createForzenBalance(ClienterForzenBalanceReq req) {
		ResponseBase rBase = new ResponseBase();
		rBase.setMessage("冻结成功");
		rBase.setResponseCode(1);
		// 生成冻结单
		ClienterForzen clienterForzen = new ClienterForzen();
		clienterForzen.setClienterid(req.getClienterId());
		clienterForzen.setForzenamount(req.getForzenAmount());
		clienterForzen.setForzenreason(req.getForzenReason());
		clienterForzen.setStatus(ClienterForzenType.Forzening.value());
		clienterForzen.setOperator(req.getOperator());
		int forzenResult = clienterForzenDao.insert(clienterForzen);
		if (forzenResult > 0) {
			// 冻结单日志
			ClienterForzenLog clienterForzenLog = new ClienterForzenLog();
			clienterForzenLog.setClienterforzenid(clienterForzen.getId());
			clienterForzenLog.setOperator(req.getOperator());
			clienterForzenLog
					.setOperatype(ClienterForzenType.Forzening.value()); // 冻结
			clienterForzenLog.setRemark("冻结骑士金额" + req.getForzenAmount());
			int iResult = clienterForzenLogDao.insert(clienterForzenLog);
			if (iResult <= 0) {
				throw new TransactionalRuntimeException("冻结骑士金额错误");
			}
			if (iResult > 0) {
				// 减骑士余额 和可提现余额
				ClienterMoney clienterMoney = new ClienterMoney();
				clienterMoney.setAmount(-req.getForzenAmount()); // 扣钱
				clienterMoney.setClienterId(req.getClienterId());
				clienterMoney
						.setRecordType(ClienterBalanceRecordRecordType.ForzenBalance
								.value());
				clienterMoney.setRelationNo(clienterForzen.getId() + "");
				clienterMoney.setWithwardId(clienterForzen.getId());
				clienterMoney.setOperator(req.getOperator());
				clienterMoney.setStatus(ClienterBalanceRecordStatus.Success
						.value());
				clienterMoney.setRemark("冻结骑士金额");
				clienterService.updateCBalanceAndWithdraw(clienterMoney);
			}
		}
		return rBase;
	}

	@Override
	public ResponseBase unfreezeClienterBalance(ClienterUnfreezeReq req) {
		ResponseBase rBase = new ResponseBase();
		rBase.setMessage("解冻成功");
		rBase.setResponseCode(1);
		// 更新解冻单状态
		ClienterForzen clienterForzen = new ClienterForzen();
		clienterForzen.setId(req.getId());
		clienterForzen.setOperator(req.getOperator());
		clienterForzen.setStatus(ClienterForzenType.HadForzen.value());
		clienterForzen.setThawreason(req.getUnfreezeReason());
		int uResult = clienterForzenDao.updateByPrimaryKey(clienterForzen);
		if (uResult > 0) {
			// 插入冻结单日志表
			ClienterForzenLog clienterForzenLog = new ClienterForzenLog();
			clienterForzenLog.setClienterforzenid(req.getId());
			clienterForzenLog.setOperator(req.getOperator());
			clienterForzenLog
					.setOperatype(ClienterForzenType.HadForzen.value()); // 解冻
			clienterForzenLog.setRemark("解冻骑士金额" + req.getForzenAmount());
			int iResult = clienterForzenLogDao.insert(clienterForzenLog);
			if(iResult <=0){
				throw new TransactionalRuntimeException("解冻骑士金额错误");
			}
			if(iResult>0){ 
				// 增加骑士余额和可提现余额
				ClienterMoney clienterMoney = new ClienterMoney();
				clienterMoney.setAmount(req.getForzenAmount()); // 加钱
				clienterMoney.setClienterId(req.getClienterId());
				clienterMoney
						.setRecordType(ClienterBalanceRecordRecordType.UnfreezeBalance
								.value());
				clienterMoney.setRelationNo(req.getId() + "");
				clienterMoney.setWithwardId(req.getId());
				clienterMoney.setOperator(req.getOperator());
				clienterMoney.setStatus(ClienterBalanceRecordStatus.Success
						.value());
				clienterMoney.setRemark("解冻骑士金额");
				clienterService.updateCBalanceAndWithdraw(clienterMoney); 
			}
		}
		return rBase;
	}
}
