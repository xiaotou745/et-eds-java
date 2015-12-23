package com.edaisong.taobaoopenapi.common;
import org.springframework.stereotype.Component;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.taobao.TaoBaoResponseBase;

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
		TaoBaoResponseBase rep=new TaoBaoResponseBase();
		rep.setIs_success(false);
		rep.setError_code(ex.getMessage());
		rep.setError_msg(StringUtils.getStackTrace(ex));
		rep.setResult(false);
        
        ResponseBuilder rb = Response.status(Response.Status.OK);
        rb.type("application/json;charset=UTF-8");
        rb.entity(rep);
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();
        return r;
    }  
}


