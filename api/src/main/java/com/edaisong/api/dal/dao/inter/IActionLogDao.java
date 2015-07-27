package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.domain.ActionLog;

public interface IActionLogDao {
	/*
	 * 系统级，记录方法的ActionLog（写入db）
	 * */
	void WriteActionLog(ActionLog logEngity);

	void WriteLog2DB(String msg);
}
