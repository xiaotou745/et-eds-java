package com.edaisong.api_http.common;

import org.apache.log4j.Logger;








import com.edaisong.api_http.entity.OpenResponseBase;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.common.ResponseCode;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Locale;

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


