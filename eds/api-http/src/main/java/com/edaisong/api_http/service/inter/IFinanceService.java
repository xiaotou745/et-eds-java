package com.edaisong.api_http.service.inter;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.req.CardBindAlipayReq;
import com.edaisong.entity.req.CardModifyAlipayReq;

/**
 * 财务管理
 * @author  pengyi 
 * @date 2015年9月10日 下午4:04:30
 * @version 1.0
 * @parameter
 * @since
 */
@Path("/finance")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IFinanceService {
	
	/**
	 * c端绑定支付宝
	 * @author pengyi
	 * @date 2015年9月11日 上午10:05:03
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/cardbindalipayc")
	public HttpResultModel<Object> cardBindAlipayC(CardBindAlipayReq req);
	
	/**
	 * c端修改支付宝
	 * @author pengyi
	 * @date 2015年9月11日 上午10:05:14
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/cardmodifyalipayc")
	public HttpResultModel<Object> cardModifyAlipayC(CardModifyAlipayReq req);
}
