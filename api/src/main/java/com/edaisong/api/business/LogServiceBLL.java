package com.edaisong.api.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import com.edaisong.api.dal.dao.inter.IActionLogDao;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.domain.ActionLog;

@Component
public class LogServiceBLL {
	@Autowired
	private IActionLogDao iActionLogDao;
	
    private static Logger globalLogger = Logger.getLogger("globalExceptionLogger");
    private static Logger logger = Logger.getLogger("apiLogger");
    
    /**
     * 系统级，记录方法的ActionLog（异步写入db和log文件）
     * @param
     */
	public void SystemActionLog(ActionLog logEngity) {
		//logger.info(logEngity);
		globalLogger.info(JsonUtil.obj2string(logEngity));
		//应该异步调用dao写入db
		//iActionLogDao.WriteActionLog(logEngity);
	}
	public void LogInfo(ActionLog logEngity) {
		logger.info(JsonUtil.obj2string(logEngity));
		globalLogger.info(logEngity);
	}
	public void LogInfo(String msg) {
		logger.info(msg);
		globalLogger.info(msg);
	}
	public void LogError(ActionLog logEngity) {
		globalLogger.info(JsonUtil.obj2string(logEngity));
	}
	public void LogError(String msg) {
		globalLogger.info(msg);
	}
}
