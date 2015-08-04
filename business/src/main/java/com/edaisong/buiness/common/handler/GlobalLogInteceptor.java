package com.edaisong.buiness.common.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edaisong.api.business.LogServiceBLL;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;
/**
 * ȫ��action��¼
 * 
 * @author zhaohailong
 */
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
			Object obj=request.getAttribute("hasException");
			if (obj!=null) {
				exceptionMsg = (String) request.getAttribute("exception");
				stackTrace = (String) request.getAttribute("stackTrace");
//				System.out.println("�쳣��Ϣ��" + exceptionMsg);
				// System.out.println("�쳣��ջ��" + stackTrace);
			}
			String appServerIP="localhost";
			List<String> ipinfoList=SystemUtils.GetLocalIpInfo();
			if (ipinfoList!=null&&ipinfoList.size()>0) {
				appServerIP=ipinfoList.get(0);
			}
			ActionLog	logEngity=new  ActionLog();
			logEngity.setSourceSys("business");
			logEngity.setClientFrom(0);
			logEngity.setAppServer(appServerIP);
			logEngity.setMethodName(methodName);
			logEngity.setParam(param);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			logEngity.setExecuteTime((end - start));
			logServiceBLL.SystemActionLog(logEngity);
			
			System.out.println("�������ƣ�" + methodName);
			System.out.println("������Σ�" + param);
			System.out.println("ִ��ʱ��,��ȷ������:" + (end - start));
		}
	}

}