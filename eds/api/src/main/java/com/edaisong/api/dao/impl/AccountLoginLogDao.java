package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountLoginLogDao;
import com.edaisong.entity.AccountLog;

@Repository
public class AccountLoginLogDao extends DaoBase implements IAccountLoginLogDao{

	@Override
	public void addLog(AccountLog log) {
		getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IAccountLoginLogDao.addLog", log);
	}

}
