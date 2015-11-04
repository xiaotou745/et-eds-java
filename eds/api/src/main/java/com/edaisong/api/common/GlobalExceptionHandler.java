package com.edaisong.api.common;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * 
 * @author zhaohailong
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		String methodName = "";
		String param = "";
		String msg = ex.getMessage();
		String stackTrace = StringUtils.getStackTrace(ex);
    	if (ex instanceof TransactionalRuntimeException) {
    		stackTrace = "";
		}

		/*
		 * 全局LogInteceptor中统一记录方法调用的actionlog和异常信息
		 * */
		request.setAttribute("exception", msg);
		request.setAttribute("stackTrace", stackTrace);

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			//如果Controller中的方法返回值不是ModelAndView，则认为该方法是ajax调用，
			//此时抛出RuntimeException，触发ajax的error处理函数,否则返回自定义错误页面
			if (handlerMethod.getMethod().getReturnType()!=ModelAndView.class) {
				throw (RuntimeException)ex;
			}
			methodName = handlerMethod.getMethod().toString();
			param = JsonUtil.obj2string(request.getParameterMap());
		}		
		Map<String, String> ext = new HashMap<>();
		ext.put("methodName", methodName);
		ext.put("param", param);
		ext.put("exception", msg);
		ext.put("stackTrace", stackTrace.replace("\r\n\t", "<br/>"));
		return new ModelAndView("common/500", ext);
	}
}
