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
import com.edaisong.entity.req.MyOrderBReq;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.OrderDetailBReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.req.IsAllowInputMoneyReq;
import com.edaisong.entity.resp.MyOrderBResp;
import com.edaisong.entity.resp.MyOrderDetailBResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;

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
	 * 获取商家是否需要录入金额才可以发单 0 需要 1 不需要  默认0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@POST
	@Path("/getisallowinputmoney")	
	public HttpResultModel<Integer> getIsAllowInputMoney(IsAllowInputMoneyReq par);
	
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
	 public HttpResultModel<List<ServiceClienters>>  getMyServiceClienters(PagedGetMyServiceClientersReq req);
	
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
	
	/*
	 * 获取我的任务B
	 * wangchao
	 */
	@POST
	@Path("/getmyorderb")
	HttpResultModel<MyOrderBResp> getMyOrdeB(MyOrderBReq myOrderBReq);
	
	/*
	 * 获取我的任务详情B
	 * wangchao
	 */
	@POST
	@Path("/getmyorderdetailb")
	public HttpResultModel<MyOrderDetailBResp> getMyOrderDetailB(OrderDetailBReq orderGrabBReq); 
}
