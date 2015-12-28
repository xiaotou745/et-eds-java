package com.edaisong.taobaoopenapi.common;

import java.util.Date;
import java.util.List;

import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.security.AES;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.ActionLog;

public class ActiveMqLogUtil {
	private static void synWriteLog(ActionLog logEngity){
		String logResultJson = "";
		boolean needSendMail = false;
		String logMsg = "";
		String logStackTrace = "";
		try {
			String logParam = AES.aesEncrypt(JsonUtil.obj2string(logEngity));
			logResultJson = HttpUtil.sendPost(PropertyUtils.getProperty("ActiveMqAddress"), 
											"{\"data\":\""+ logParam+"\"}",
											"application/json; charset=utf-8");
			if (logResultJson != null && !logResultJson.isEmpty()) {
				HttpResultModel<Object> obj = JsonUtil.str2obj(logResultJson,HttpResultModel.class);
				if (obj != null&& obj.getStatus() != HttpReturnRnums.Success.value()) {
					needSendMail = true;
				}
			}
		} catch (Exception e) {
			needSendMail = true;
			logMsg = e.getMessage();
			logStackTrace = StringUtils.getStackTrace(e);
		}
		if (needSendMail) {
			String isSendMail = PropertyUtils.getProperty("IsSendMail");
			if (isSendMail.equals("1")) {
				List<String> ipinfoList = SystemUtils.getLocalIpInfo();
				String appServerIP = JsonUtil.obj2string(ipinfoList);
				SystemUtils.sendAlertEmail("taobaoopenapi_writeLog_java项目预警","appServerIP:"+appServerIP+"\n" + logMsg +"\n"+ logStackTrace);
			}
		}
	}
	public static void writeLog(ActionLog logEngity) {
		Thread dThread = new Thread(new Runnable() {
			@Override
			public void run() {
				synWriteLog(logEngity);
			}
		});
		dThread.setDaemon(false);
		dThread.start();
	}

	public static void writeLog(String url, String param, String decryptMsg,
			String resultJson, String exceptionMsg, String stackTrace,
			Date requestTime) {

		Date endDate = new Date();
		List<String> ipinfoList = SystemUtils.getLocalIpInfo();
		String appServerIP = JsonUtil.obj2string(ipinfoList);
		ActionLog logEngity = new ActionLog();
		logEngity.setUserID(-1);
		logEngity.setUserName("");
		logEngity.setRequestType(0);
		logEngity.setClientIp("");
		logEngity.setSourceSys("taobaoopenapi");
		logEngity.setRequestUrl(url);
		logEngity.setParam(param);
		logEngity.setDecryptMsg(decryptMsg);
		logEngity.setContentType("application/json; charset=utf-8");
		logEngity.setHeader("");
		logEngity.setRequestMethod("POST");
		logEngity.setMethodName("");
		logEngity.setResultJson(resultJson);
		logEngity.setAppServer(appServerIP);
		logEngity.setException(exceptionMsg);
		logEngity.setStackTrace(stackTrace);
		logEngity.setExecuteTime(endDate.getTime() - requestTime.getTime());
		logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
		logEngity.setRequestEndTime(ParseHelper.ToDateString(endDate, ""));
		writeLog(logEngity);
	}
}
