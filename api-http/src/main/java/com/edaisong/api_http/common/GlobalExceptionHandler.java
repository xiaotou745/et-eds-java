package com.edaisong.api_http.common;

import org.apache.cxf.message.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.api.common.LogServiceBLL;
import com.edaisong.api_http.entity.OpenResponseBase;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.ActionLog;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Date;
import java.util.List;
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
        rep.setStatus(HttpReturnRnums.SystemError.value());
        rep.setMessage(HttpReturnRnums.SystemError.desc());
        rep.setResult(StringUtils.getStackTrace(ex));
        
        ResponseBuilder rb = Response.status(Response.Status.OK);
        rb.type("application/json;charset=UTF-8");
        rb.entity(rep);
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();
        return r;
    }  
}


