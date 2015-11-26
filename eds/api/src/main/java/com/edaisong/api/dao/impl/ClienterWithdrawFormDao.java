package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterWithdrawFormDao;
import com.edaisong.entity.domain.AlipayBatchClienterWithdrawForm;
/**
 * 骑士提现单
 * 
 * @author CaoHeYang
 * @date 20151020
 */
@Repository
public class ClienterWithdrawFormDao extends DaoBase implements IClienterWithdrawFormDao {
	/**
	 * 根据批次号查询提现单
	 * 
	 * @author CaoHeYang
	 * @date 20151020
	 * @param id
	 * @return
	 */
	@Override
	public List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(Long id) {
	 	return getReadOnlySqlSessionUtil().selectList(
			"IClienterWithdrawFormDao.getClienterWithdrawFormByBatchNo", id);
	}
}
