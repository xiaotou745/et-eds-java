package com.edaisong.api_http.common;


import org.springframework.stereotype.Component;



import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.common.HttpResultModel;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import java.util.Locale;

/**
 * 全局异常处理
 * 
 * @author zhaohailong
 */
@Component
public class GlobalExceptionHandler implements ExceptionMapper {
	@Override
    public Response toResponse(Throwable ex) {
    	HttpResultModel<String> rep=new HttpResultModel<String>();
    	if (ex instanceof TransactionalRuntimeException||
    		ex instanceof InvalidFormatException) {
	        rep.setStatus(HttpReturnRnums.ParaError.value());
	        rep.setMessage(HttpReturnRnums.ParaError.desc());
	        rep.setResult(ex.getMessage());
		}else {
	        rep.setStatus(HttpReturnRnums.SystemError.value());
	        rep.setMessage(HttpReturnRnums.SystemError.desc());
	        rep.setResult(StringUtils.getStackTrace(ex));
		}
        
        ResponseBuilder rb = Response.status(Response.Status.OK);
        rb.type("application/json;charset=UTF-8");
        rb.entity(rep);
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();
        return r;
    }  
}


