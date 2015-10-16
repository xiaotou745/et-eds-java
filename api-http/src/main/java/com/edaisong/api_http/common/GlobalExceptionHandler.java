package com.edaisong.api_http.common;

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
	@Autowired
	LogServiceBLL logServiceBLL;
	@Override
    public Response toResponse(Throwable ex) {
        //loginfo(ex);
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
	private void loginfo(Throwable ex){
		String appServerIP = "localhost";
		List<String> ipinfoList = SystemUtils.getLocalIpInfo();
		if (ipinfoList != null && ipinfoList.size() > 0) {
			appServerIP = ipinfoList.get(0);
		}
		ActionLog logEngity = new ActionLog();
		logEngity.setSourceSys("apihttp");
		logEngity.setClientFrom(0);//暂时没用
		logEngity.setRequestType(0);
		logEngity.setAppServer(appServerIP);
//		logEngity.setMethodName(methodName);
//		logEngity.setParam(param);
		logEngity.setException(ex.getMessage());
		logEngity.setStackTrace(StringUtils.getStackTrace(ex));
//		logEngity.setExecuteTime(0d);
//
//		Date requestTime = (Date) request.getAttribute("requestTime");
//		logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
//		logEngity.setRequestEndTime(ParseHelper.ToDateString(new Date(), ""));
//		
//		logEngity.setUserID(userID);
//		logEngity.setUserName(userName);

		logServiceBLL.SystemActionLog(logEngity);
	}
}


