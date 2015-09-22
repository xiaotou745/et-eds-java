package com.edaisong.api.common;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
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
	private static Field[] fields = ActionLog.class.getDeclaredFields();
	/**
	 * 系统级，记录方法的ActionLog（异步写入db和log文件）
	 * 
	 * @param
	 */
	public void SystemActionLog(ActionLog logEngity) {
		return ;
/*
		try {
			initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
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
		} catch (Exception e) {
		}
*/
	}

	public void LogInfo(ActionLog logEngity) {
	}

	public void LogInfo(String msg) {
	}

	public void LogError(ActionLog logEngity) {
	}

	public void LogError(String msg) {

	}

	private void initLog4DB(ActionLog logEngity) {
		try {
			MDC.clear();
			for (Field field : fields) {
				field.setAccessible(true);
				MDC.put(field.getName(), field.get(logEngity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
