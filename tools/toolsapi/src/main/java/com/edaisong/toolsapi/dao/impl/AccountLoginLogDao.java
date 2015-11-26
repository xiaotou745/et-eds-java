package com.edaisong.toolsapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAccountLoginLogDao;
import com.edaisong.toolsentity.AccountLog;

@Repository
public class AccountLoginLogDao extends DaoBase implements IAccountLoginLogDao{

	@Override
	public void addLog(AccountLog log) {
		getMasterSqlSessionUtil().insert("IAccountLoginLogDao.addLog", log);
	}

}
