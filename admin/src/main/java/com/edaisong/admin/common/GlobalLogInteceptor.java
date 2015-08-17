package com.edaisong.admin.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.api.business.LogServiceBLL;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;

public class GlobalLogInteceptor extends HandlerInterceptorAdapter {
	@Autowired
	LogServiceBLL logServiceBLL;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			request.setAttribute("start", System.currentTimeMillis());
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
				exceptionMsg = "系统异常:" + (objMsg == null ? "null" : objMsg.toString());
				stackTrace = obj.toString();
			} else if (ex != null) {
				exceptionMsg = "页面解析异常:" + ex.getMessage();
				stackTrace = StringUtils.getStackTrace(ex);
			}
			if (exceptionMsg != null && !exceptionMsg.isEmpty()) {
				// System.out.println("异常信息：" + exceptionMsg);
				// System.out.println("异常堆栈：" + stackTrace);
			}
			String appServerIP = "localhost";
			List<String> ipinfoList = SystemUtils.GetLocalIpInfo();
			if (ipinfoList != null && ipinfoList.size() > 0) {
				appServerIP = ipinfoList.get(0);
			}
			ActionLog logEngity = new ActionLog();
			logEngity.setSourceSys("admin");
			logEngity.setClientFrom(0);
			logEngity.setAppServer(appServerIP);
			logEngity.setMethodName(methodName);
			logEngity.setParam(param);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			logEngity.setExecuteTime((end - start));
			logServiceBLL.SystemActionLog(logEngity);

			System.out.println("方法名称：" + methodName);
			System.out.println("方法入参：" + param);
			System.out.println("执行时间,精确到毫秒:" + (end - start));
		}
	}

}
