package com.edaisong.toolsapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IActionLogDao;
import com.edaisong.toolsentity.domain.ActionLog;

@Repository
public class ActionLogDao extends DaoBase implements IActionLogDao {

	@Override
	public void writeActionLog(ActionLog logEngity) {
		getMasterSqlSessionUtil().insert(
				"com.edaisong.toolsapi.dao.inter.IActionLogDao.WriteActionLog",
				logEngity);

	}

	@Override
	public void writeLog2DB(String msg) {
		getMasterSqlSessionUtil().insert(
				"com.edaisong.toolsapi.dao.inter.IActionLogDao.WriteActionLog",
				msg);

	}

}
