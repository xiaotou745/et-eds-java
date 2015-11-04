package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.req.GetPushOrderTypeReq;
import com.edaisong.entity.resp.GetMyServiceClientersResp;

/**
 *  商家相关 
 * @author CaoHeYang
 * @date 20151030
 */
@Path("/business")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IBusinessHttpService {

	/**
	 * 获取门店发单模式：0 普通模式（默认），1 快单模式   默认0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@POST
	@Path("/getpushordertype")	
	public HttpResultModel<Integer> getPushOrderType(GetPushOrderTypeReq par);
	
	/**
	 * 绑定骑士商户
	 * wangchao
	 */
	@POST
	@Path("/bindclienterbusiness")
	public HttpResultModel<Object> bindClienterBusiness(BindClienterBusiness bindClienterBusiness);
	
	/**
	 * 商戶端 我的骑士
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@POST
	@Path("/getmyserviceclienters")	
	 public HttpResultModel<GetMyServiceClientersResp>  getMyServiceClienters(PagedGetMyServiceClientersReq req);
	
	/**
	 * 商戶端 我的骑士   申请中 同意/拒绝功能
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@POST
	@Path("/optbindclienter")	
	 public HttpResultModel<Object> optBindClienter(OptBindClienterReq req);
	
	/**
	 * 商家解绑
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@POST
	@Path("/removerelation")	
	 public HttpResultModel<Object> removeRelation(ClienterBindOptionReq req);
}
