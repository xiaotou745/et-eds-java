package com.edaisong.api.dal.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IActionLogDao;
import com.edaisong.entity.domain.ActionLog;

@Repository
public class ActionLogDao extends DaoBase implements IActionLogDao {

	@Override
	public void WriteActionLog(ActionLog logEngity) {
		getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IActionLogDao.WriteActionLog",
				logEngity);

	}

	@Override
	public void WriteLog2DB(String msg) {
		getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IActionLogDao.WriteActionLog",
				msg);

	}

}
