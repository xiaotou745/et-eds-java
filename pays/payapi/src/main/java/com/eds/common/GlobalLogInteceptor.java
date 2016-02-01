package com.eds.common;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.eds.entity.ActionLog;

public class GlobalLogInteceptor extends HandlerInterceptorAdapter {
	@Autowired
	LogServiceBLL logServiceBLL;
	private String sourceSys;

	public String getSourceSys() {
		return sourceSys;
	}

	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			request.setAttribute("requestTime", (new Date()));
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;

			String methodName = handlerMethod.getMethod().toString();
			String param = JsonUtil.obj2string(request.getParameterMap());
			Date requestTime = (Date) request.getAttribute("requestTime");
			int userID=ParseHelper.ToInt(request.getAttribute("userID"),-1);
			String userName = ParseHelper.ToString(request.getAttribute("userName"),"");
			
			String headers = JsonUtil.obj2string(getHeadersInfo(request));
			String exceptionMsg = "";
			String stackTrace = "";
			// 记录系统异常
			Object obj = request.getAttribute("stackTrace");
			if (obj != null) {
				Object objMsg = request.getAttribute("exception");
				exceptionMsg = (objMsg == null ? "" : objMsg.toString());
				stackTrace = obj.toString();
			} else if (ex != null&&!(ex instanceof AjaxNotLoginRunTimeException)) {
				exceptionMsg = ex.getMessage() == null ? "" : ex.getMessage();
				stackTrace = StringUtils.getStackTrace(ex);
			}
			String clientIp = SystemUtils.getClientIp(request);
			
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			String appServerIP = JsonUtil.obj2string(ipinfoList);

			ActionLog logEngity = new ActionLog();
			logEngity.setClientIp(clientIp);
			logEngity.setRequestUrl(request.getRequestURL().toString());
			logEngity.setDecryptMsg("");
			logEngity.setResultJson("");
			logEngity.setContentType(request.getContentType());
			logEngity.setHeader(headers);
			logEngity.setRequestMethod(request.getMethod());
			logEngity.setUserID(userID);
			logEngity.setUserName(userName);
			logEngity.setSourceSys(getSourceSys());
			logEngity.setAppServer(appServerIP);
			logEngity.setMethodName(methodName);
			logEngity.setParam(param);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			Date endDate = new Date();
			logEngity.setExecuteTime(endDate.getTime() - requestTime.getTime());
			logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
			logEngity.setRequestEndTime(ParseHelper.ToDateString(endDate, ""));

			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				logEngity.setRequestType(1);
			} else {
				logEngity.setRequestType(0);
			}
			logServiceBLL.SystemActionLog(logEngity);
			
			System.out.println("方法名称：" + methodName);
			System.out.println("方法入参：" + param);
			System.out.println("执行时间,精确到毫秒:" + (endDate.getTime() - requestTime.getTime()));
		}
	}

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
}