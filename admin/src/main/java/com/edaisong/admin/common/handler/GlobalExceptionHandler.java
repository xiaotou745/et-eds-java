package com.edaisong.admin.common.handler;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.core.util.ExceptionUtil;

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
	/**
	 * 全局异常记录logger
	 */
	private static Logger logger = Logger.getLogger("globalExceptionLogger");

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String msg = handler.getClass().getName() + ex.getMessage();
		String stackTrace = ExceptionUtil.getStackTrace(ex);
		logger.info(msg + stackTrace);
		Map<String, String> ext = new HashMap<>();
		ext.put("exception", ex.getMessage());
		ext.put("stackTrace", stackTrace);
		return new ModelAndView("common/exception", ext);
	}
}
