package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterAllowWithdrawRecordDao;
import com.edaisong.entity.ClienterAllowWithdrawRecord;
@Repository
public class ClienterAllowWithdrawRecordDao extends DaoBase  implements
		IClienterAllowWithdrawRecordDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public int insert(ClienterAllowWithdrawRecord record) {
		int result = getMasterSqlSessionUtil().insert("IClienterAllowWithdrawRecordDao.insert",
				record);
		return result;
	}

	@Override
	public int insertSelective(ClienterAllowWithdrawRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterAllowWithdrawRecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterAllowWithdrawRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterAllowWithdrawRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
