package com.edaisong.api.common;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.api.activemq.ActiveMqService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;
import com.edaisong.entity.domain.ServiceLog;

@Component
public class LogServiceBLL {
	@Autowired
	private ActiveMqService activeMqService;

	private static Logger businessLogger = Logger.getLogger("businessLogger");
	private static Logger adminLogger = Logger.getLogger("adminLogger");
	private static Logger apiHttpLogger = Logger.getLogger("apiHttpLogger");
	private static Logger taobaoopenapiLogger = Logger.getLogger("taobaoopenapiLogger");
	private static Field[] fields = ActionLog.class.getDeclaredFields();
	private static Field[] serviceFields = ServiceLog.class.getDeclaredFields();

	/**
	 * 系统级，记录方法的ActionLog（异步写入db和log文件）
	 * 
	 * @param
	 */
	public void SystemActionLog(ActionLog logEngity) {
		String isSendMail = PropertyUtils.getProperty("IsSendMail");
		try {
			if (logEngity.getStackTrace() != null&& !logEngity.getStackTrace().isEmpty()) {
				if (isSendMail.equals("1")) {
					String alertBody = getAlertBody(logEngity);
					if (alertBody != null && !alertBody.isEmpty()) {
						SystemUtils.sendAlertEmail(logEngity.getSourceSys()+ "_java项目预警", alertBody);
					}
				}
			}
			// initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
			writeFile(logEngity.getSourceSys(), jsonMsg);
			activeMqService.asynSendMessage(logEngity.getSourceSys(),jsonMsg);
		} catch (Exception e) {
			if (isSendMail.equals("1")) {
				List<String> ipinfoList = SystemUtils.getLocalIpInfo();
				String appServerIP = JsonUtil.obj2string(ipinfoList);
				SystemUtils.sendAlertEmail(logEngity.getSourceSys()+ "_SystemActionLog_java项目预警", "appServerIP:"+appServerIP+"\n"+e.getMessage()+StringUtils.getStackTrace(e));
			}
		}
	}

	/**
	 * 记录服务的ActionLog（异步写入db和log文件）
	 * 
	 * @param
	 */
	public void ServiceActionLog(ServiceLog logEngity) {
		String isSendMail = PropertyUtils.getProperty("IsSendMail");
		try {
			if (logEngity.getStackTrace() != null&& !logEngity.getStackTrace().isEmpty()) {
				if (isSendMail.equals("1")) {
					String alertBody = getAlertBody(logEngity);
					if (alertBody != null && !alertBody.isEmpty()) {
						SystemUtils.sendAlertEmail(logEngity.getSourceSys()+ "_java项目预警", alertBody);
					}
				}
			}
			// initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
			writeFile(logEngity.getSourceSys(), jsonMsg);
			activeMqService.asynSendServiceLogMessage(jsonMsg);
		} catch (Exception e) {
			if (isSendMail.equals("1")) {
				List<String> ipinfoList = SystemUtils.getLocalIpInfo();
				String appServerIP = JsonUtil.obj2string(ipinfoList);
				SystemUtils.sendAlertEmail(logEngity.getSourceSys()+ "_ServiceActionLog_java项目预警", "appServerIP:"+appServerIP+"\n"+e.getMessage()+StringUtils.getStackTrace(e));
			}
		}
	}

	private void writeFile(String sourceSys, String jsonMsg) {
		switch (sourceSys) {
		case "admin":
			adminLogger.info(jsonMsg);
			break;
		case "business":
			businessLogger.info(jsonMsg);
			break;
		case "apihttp":
			apiHttpLogger.info(jsonMsg);
			break;
		case "taobaoopenapi":
			taobaoopenapiLogger.info(jsonMsg);
			break;
		default:
			break;
		}
	}

	private String getAlertBody(Object logEngity) {
		try {
			StringBuilder sb = new StringBuilder();
			String stackTrace = "";
			String propertyValue = "";
			Field[] tempFields = fields;
			if (logEngity instanceof ServiceLog) {
				tempFields = serviceFields;
			}
			for (Field field : tempFields) {
				field.setAccessible(true);
				propertyValue = field.get(logEngity) == null ? "null" : field.get(logEngity).toString();
				if (field.getName().equals("stackTrace")) {
					stackTrace = field.getName() + ":" + propertyValue;
				} else {
					sb.append(field.getName() + ":" + propertyValue + "\n");
				}
			}
			sb.append(stackTrace);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private void initLog4DB(Object logEngity) {
		try {
			MDC.clear();
			String propertyValue = "";
			Field[] tempFields = fields;
			if (logEngity instanceof ServiceLog) {
				tempFields = serviceFields;
			}
			for (Field field : tempFields) {
				field.setAccessible(true);
				propertyValue = field.get(logEngity) == null ? "null" : field.get(logEngity).toString();
				MDC.put(field.getName(), propertyValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
