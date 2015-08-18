package com.edaisong.api.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




import com.edaisong.api.dao.inter.IActionLogDao;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.domain.ActionLog;

@Component
public class LogServiceBLL {
	@Autowired
	private IActionLogDao iActionLogDao;
	
    private static Logger businessLogger = Logger.getLogger("businessLogger");
    private static Logger adminLogger = Logger.getLogger("adminLogger");
    private static Logger apiHttpLogger = Logger.getLogger("apiHttpLogger");
    /**
     * 系统级，记录方法的ActionLog（异步写入db和log文件）
     * @param
     */
	public void SystemActionLog(ActionLog logEngity) {
		if (logEngity.getException()==null||
		    logEngity.getException().isEmpty()) {
			return;
		}
		String jsonMsg=JsonUtil.obj2string(logEngity);
		switch (logEngity.getSourceSys()) {
		case "admin":
			adminLogger.info(jsonMsg);
			break;
		case "business":
			businessLogger.info(jsonMsg);
			break;
		case "apiHttp":
			apiHttpLogger.info(jsonMsg);
			break;
		default:
			break;
		}
		//应该异步调用dao写入db
		//iActionLogDao.WriteActionLog(logEngity);
	}
	public void LogInfo(ActionLog logEngity) {
	}
	public void LogInfo(String msg) {
	}
	public void LogError(ActionLog logEngity) {
	}
	public void LogError(String msg) {

	}
}
