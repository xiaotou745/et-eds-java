package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterWithdrawFormDao;
import com.edaisong.api.service.inter.IClienterWithdrawFormService;
import com.edaisong.entity.domain.AlipayBatchClienterWithdrawForm;

/**
 * 骑士提现单
 * 
 * @author CaoHeYang
 * @date 20151020
 */
@Service
public class ClienterWithdrawFormService implements IClienterWithdrawFormService {
	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;

	/**
	 * 根据批次号查询提现单
	 * 
	 * @author CaoHeYang
	 * @date 20151020
	 * @param  id
	 * @return
	 */
	@Override
	public List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(Long id) {
		return clienterWithdrawFormDao.getClienterWithdrawFormByBatchNo(id);
	}
}
