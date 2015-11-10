package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.Business;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BusinessBasicInfoModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.BCheckCodeReq;
import com.edaisong.entity.req.BSendCodeReq;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.MyOrderBReq;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.OrderDetailBReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.req.GetPushOrderTypeReq;
import com.edaisong.entity.resp.GetMyServiceClientersResp;
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
	
	/*
	 * 获取商户基础信息
	 * wangchao
	 */
	@POST
	@Path("/getbusinessinfo")
	public HttpResultModel<BusinessBasicInfoModel> getBusinessInfo(BusinessReq businessReq); 
	/**
	 *  B端发送短信验证码
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	@POST
	@Path("/sendcode")
	public HttpResultModel<Object> sendCode(BSendCodeReq req);
    
	/**
	 * 商家端修改绑定手机号第一步验证当前手机号
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	@POST
	@Path("/businessmodiyphonestep1")
	public HttpResultModel<Object> businessModiyPhoneStep1(BCheckCodeReq req);
	/**
	 * 商家端修改绑定手机号第二步验证新手机号
	 * @author CaoHeYang
	 * @date 201551110
	 * @param req
	 * @return
	 */
	@POST
	@Path("/businessmodiyphonestep2")
	public HttpResultModel<Object> businessModiyPhoneStep2(BCheckCodeReq req);
}
