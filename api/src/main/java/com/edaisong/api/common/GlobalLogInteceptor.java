package com.edaisong.api.common;

import java.util.Date;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.api.common.LogServiceBLL;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;

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
			request.setAttribute("start", System.currentTimeMillis());
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
			long end = System.currentTimeMillis();
			long start = (Long) request.getAttribute("start");

			String exceptionMsg = "";
			String stackTrace = "";
			// 记录系统异常
			Object obj = request.getAttribute("stackTrace");
			if (obj != null) {
				Object objMsg = request.getAttribute("exception");
				exceptionMsg = (objMsg == null ? "" : objMsg.toString());
				stackTrace = obj.toString();
			} else if (ex != null) {
				exceptionMsg = ex.getMessage() == null ? "" : ex.getMessage();
				stackTrace = StringUtils.getStackTrace(ex);
			}
			if (exceptionMsg != null && !exceptionMsg.isEmpty()) {
				// System.out.println("异常信息：" + exceptionMsg);
				// System.out.println("异常堆栈：" + stackTrace);
			}
			String appServerIP = "localhost";
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			if (ipinfoList != null && ipinfoList.size() > 0) {
				appServerIP = ipinfoList.get(0);
			}

			ActionLog logEngity = new ActionLog();
			logEngity.setSourceSys(getSourceSys());
			logEngity.setClientFrom(0);//暂时没用
			logEngity.setAppServer(appServerIP);
			logEngity.setMethodName(methodName);
			logEngity.setParam(param);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			logEngity.setExecuteTime((end - start));

			Date requestTime = (Date) request.getAttribute("requestTime");
			logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
			logEngity.setRequestEndTime(ParseHelper
					.ToDateString(new Date(), ""));

			int userID=ParseHelper.ToInt(request.getAttribute("userID"),-1);
			String userName = ParseHelper.ToString(request.getAttribute("userName"),"");
			
			logEngity.setUserID(userID);
			logEngity.setUserName(userName);
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				logEngity.setRequestType(1);
			} else {
				logEngity.setRequestType(0);
			}
			logServiceBLL.SystemActionLog(logEngity);

			System.out.println("方法名称：" + methodName);
			System.out.println("方法入参：" + param);
			System.out.println("执行时间,精确到毫秒:" + (end - start));
		}
	}
}
