package com.edaisong.api.dao.inter;

import com.edaisong.entity.domain.ActionLog;

public interface IActionLogDao {
	/*
	 * 系统级，记录方法的ActionLog（写入db）
	 * */
	void writeActionLog(ActionLog logEngity);

	void writeLog2DB(String msg);
}
