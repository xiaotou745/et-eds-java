package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.domain.AlipayBatchClienterWithdrawForm;

/**
 * 骑士提现单
 * 
 * @author CaoHeYang
 * @date 20151020
 */
public interface IClienterWithdrawFormService {
	/**
	 * 根据批次号查询提现单
	 * 
	 * @author CaoHeYang
	 * @date 20151020
	 * @param id
	 * @return
	 */
	public List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(Long id);
}
