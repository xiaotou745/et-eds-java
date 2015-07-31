package com.edaisong.api_http.service;

import org.apache.log4j.Logger;





import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.common.OpenResponseBase;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.common.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 全局异常处理
 * 
 * @author zhaohailong
 */
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {
	/**
	 * 全局异常记录logger
	 */
	private static Logger logger = Logger.getLogger("globalExceptionLogger");

    public Response toResponse(Exception ex) {
    	String stackTrace = ex.getMessage()+";"+StringUtils.getStackTrace(ex);
    	logger.info("restful:" + stackTrace);

//        StackTraceElement[] trace = new StackTraceElement[1];
//        trace[0] = ex.getStackTrace()[0];
//        ex.setStackTrace(trace);
    	OpenResponseBase rep=new OpenResponseBase();
        rep.setResponseCode(ResponseCode.SYSTEM_ERROR);
        rep.setMessage(stackTrace);
        System.out.println("处理自定义的异常");
        ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        rb.type("application/json;charset=UTF-8");
        rb.entity(rep);
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();
        return r;
    }
    
}


