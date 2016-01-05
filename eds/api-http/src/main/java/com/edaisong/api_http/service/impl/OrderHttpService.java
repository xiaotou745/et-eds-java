package com.edaisong.api_http.service.impl;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.TransactionalRuntimeException;
import com.edaisong.api.service.inter.IOrderChildService;
import com.edaisong.api.service.inter.IOrderDraftService;
import com.edaisong.api.service.inter.IOrderGrabService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IOrderTipService;
import com.edaisong.api_http.service.inter.IOrderHttpService;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.enums.returnenums.InStoreTaskReturnEnum;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderDraft;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.QueryOrder; 
import com.edaisong.entity.req.OrderBlancePayReq;
import com.edaisong.entity.req.OrderChildCancelReq;
import com.edaisong.entity.req.OrderDetailReq;
import com.edaisong.entity.req.OrderDraftReq;
import com.edaisong.entity.req.OrderDraftGetReq;
import com.edaisong.entity.req.OrderDraftReturnReq;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.QueryOrderReq;  
import com.edaisong.entity.req.QueryShanSongOrderReq;
import com.edaisong.entity.req.ShanSongPushOrderReq;
import com.edaisong.entity.resp.OrderBlancePayResp;
import com.edaisong.entity.resp.OrderDetailResp;
import com.edaisong.entity.resp.OrderDraftResp;
import com.edaisong.entity.resp.OrderGrabResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.OrderTipDetailResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.QueryOrderCResp;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.req.OrderDraftReq;

/**
 * 订单模块
 * 
 * @author CaoHeYang
 * @date 20150910
 */
@Service
public class OrderHttpService implements IOrderHttpService {

	@Override
	public String testVal() {
		// TODO Auto-generated method stub
		return "123";
	}

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IOrderChildService orderChildService;
	
	@Autowired
	private IOrderDraftService orderDraftService;
	
	
	@Autowired
	private IOrderTipService orderTipService;

	// region 快单模式

	/**
	 * 发布订单  快单模式
	 * @author 胡灵波
	 * @date 2015年10月30日 11:29:00
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<OrderResp> Push(OrderReq req) {

		HttpResultModel<OrderResp> resp=new HttpResultModel<OrderResp>();
		
		try
		{
			resp=orderService.PushOrder(req);	
		}
		catch(TransactionalRuntimeException err)
		{
			resp.setMessage(err.getMessage());
			resp.setStatus(HttpReturnRnums.ParaError.value());
		}			
	
		return resp;
	}	

    /**
     * 取消订单  快单模式
     * 测试  暂时不用  取消前一天智能调度发单且未被抢单的子订单时
     * @param 日期
     * @author 胡灵波
     * @Date 2015年11月5日 11:40:37
     * @return
     */
	@Override
	public HttpResultModel<OrderGrabResp> CancelOrderChild(OrderChildCancelReq  req)
	{
		HttpResultModel<OrderGrabResp> resp= orderChildService.cancelOrderChild(req);			
		return resp;
	}	
	
	// endreigon 
	
	// region 闪送模式
	
	/**闪送模式
	 * 发布订单
	 * @author 胡灵波
	 * @date 2015年11月24日 13:24:18
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<OrderResp> FlashPush(OrderDraftReq req)
	{

		HttpResultModel<OrderResp> resp=new HttpResultModel<OrderResp>();
		
		try
		{
			resp=orderService.FlashPushOrder(req);	
		}
		catch(TransactionalRuntimeException err)
		{
			resp.setMessage(err.getMessage());
			resp.setStatus(HttpReturnRnums.ParaError.value());
		}			
	
		return resp;	
	}	

	/**
	 * 余额付款 闪送模式
	 * @author 胡灵波
	 * @date 2015年12月14日 11:12:23
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override 
	public HttpResultModel<OrderBlancePayResp> OrderBalancePay(OrderBlancePayReq req)
	{
		return orderService.OrderBalancePay(req);
	}
	

	/**闪送模式
	 * 获取订单详情
	 * @author 胡灵波
	 * @date 2015年11月27日 11:46:38
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<OrderDetailResp> GetOrderDetails(OrderDetailReq req)
	{
		return orderService.GetOrderDetails(req);
	}


	/**
	 * 获取小费订单详情 闪送模式
	 * @author 胡灵波
	 * @date 2015年12月3日 13:36:24
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<OrderTipDetailResp> GetOrderTipDetails() {
		
		OrderTipDetailResp resp=new OrderTipDetailResp();
		resp.setList(orderTipService.getList());
		
		
		HttpResultModel<OrderTipDetailResp> hrm=new HttpResultModel<OrderTipDetailResp>();
		hrm.setResult(resp);
		return hrm;
	}	


	// endregion
	/**
	 * B端任务统计接口
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<OrderStatisticsBResp> orderStatisticsB(OrderStatisticsBReq para) {
		if (para.getMonthInfo() == null || para.getMonthInfo().trim().isEmpty() || para.getBusinessId() == 0 ) {
			HttpResultModel<OrderStatisticsBResp> result=new HttpResultModel<OrderStatisticsBResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result;  
		}
		return orderService.getOrderStatisticsB(para);
	}

	/**
	 * B 端首页 订单列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<QueryOrderBResp> queryOrderB(QueryOrderReq para) {
		if ( para.getBusinessId() == null
				|| para.getBusinessId() == 0
				|| (para.getStatus() != OrderStatus.New.value() && para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			HttpResultModel<QueryOrderBResp> result=new HttpResultModel<QueryOrderBResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result;   
		}
		para.setDateInfo(null);
		para.setClienterId(null);
		return orderService.queryOrderB(para);
	}

	/**
	 *  C 端我的任务
	 * 
	 * @author CaoHeYang
	 * @date 20150911
	 * @param para
	 * @return
	 */
	@Override
	public HttpResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq para) {
		para.setPageSize(100);//TODO  该接口暂时不做分页 目前只取100条数据 茹化肖
		if ( para.getClienterId() == null
				|| para.getClienterId() == 0
				|| ( para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value())) {
			HttpResultModel<QueryOrderCResp> result=new HttpResultModel<QueryOrderCResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		para.setDateInfo(null);
		para.setBusinessId(null);
		return orderService.queryOrderC(para);
	}
	/**
	 * B端已完成任务列表或者配送员配送列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrderB(QueryOrderReq para) {
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getBusinessId() == null || para.getBusinessId() == 0) {
			HttpResultModel<List<QueryOrder>> result=new HttpResultModel<List<QueryOrder>>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		return orderService.getCompliteOrder(para,0);
	}

	/**
	 * C端已完成任务列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrderC(QueryOrderReq para) {
		para.setPageSize(1000);//TODO 因为WIKI接口没有写分页参数,暂时一次性将所有数据返回,
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getClienterId() == null || para.getClienterId() == 0) {
			HttpResultModel<List<QueryOrder>> result=new HttpResultModel<List<QueryOrder>>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		return orderService.getCompliteOrder(para,1);
	}

	/**
	 * C端任务统计接口
	 * 
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	@Override
	public HttpResultModel<OrderStatisticsCResp> orderStatisticsC(OrderStatisticsCReq orderStatisticsCReq) {
		OrderStatisticsCResp orderStatisticsResp = orderService.getOrderStatisticsC(orderStatisticsCReq);
		HttpResultModel<OrderStatisticsCResp> httpResultModel = new HttpResultModel<OrderStatisticsCResp>();
		httpResultModel.setResult(orderStatisticsResp);
		return httpResultModel;
	}
	/**
	 *  骑士端获取店内任务
	 * @version 3.0  
	 * @author CaoHeYang
	 * @date 20151030
	 * @param para
	 * @return
	 */
	@Override
	public HttpResultModel<List<InStoreTask>>  getInStoreTask(InStoreTaskReq para){
		 HttpResultModel<List<InStoreTask>> res=new  HttpResultModel<List<InStoreTask>>();
		 if (para.getClienterId()==0) {
			return res.setStatus(InStoreTaskReturnEnum.ClienterIdError.value()).setMessage(InStoreTaskReturnEnum.ClienterIdError.desc());
	     }
		 if (para.getLongitude()==null||para.getLongitude()==0||para.getLatitude()==null||para.getLatitude()==0) {
				return res.setStatus(InStoreTaskReturnEnum.LocationError.value()).setMessage(InStoreTaskReturnEnum.LocationError.desc());
		  }
		 //商户下的区域不足九个不返回任务数据
		 List<InStoreTask> instoreTaskList= orderService.getInStoreTask(para);
		 res.setResult(instoreTaskList);
		 return res;
	}
	/**
	 * C端任务统计接口 
	 * @author wangchao
	 * @param data
	 */
	@Override
	public HttpResultModel<OrderStatisticsCResp> orderGrabStatisticsC(
			OrderStatisticsCReq orderStatisticsCReq) {
		OrderStatisticsCResp orderStatisticsResp = orderService.getOrderGrabStatisticsC(orderStatisticsCReq);
		HttpResultModel<OrderStatisticsCResp> httpResultModel = new HttpResultModel<OrderStatisticsCResp>();
		httpResultModel.setResult(orderStatisticsResp);
		return httpResultModel;
	}

	/**
	 * B端任务统计接口
	 * 
	 * @author wangchao 
	 * @param data
	 * @return
	 */
	@Override
	public HttpResultModel<OrderStatisticsBResp> orderGrabStatisticsB(OrderStatisticsBReq para) {
		if (para.getMonthInfo() == null || para.getMonthInfo().trim().isEmpty() || para.getBusinessId() == 0 ) {
			HttpResultModel<OrderStatisticsBResp> result=new HttpResultModel<OrderStatisticsBResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result;  
		}
		return orderService.getOrderGrabStatisticsB(para);
	}

	@Override
	public HttpResultModel<List<QueryOrder>> getCompliteOrderGrabB(
			QueryOrderReq para) {
		if (para.getDateInfo() == null || para.getDateInfo().trim().isEmpty() || para.getBusinessId() == null || para.getBusinessId() == 0) {
			HttpResultModel<List<QueryOrder>> result=new HttpResultModel<List<QueryOrder>>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result; 
		}
		return orderService.getCompliteOrderGrab(para,0);
	}

	@Override
	public HttpResultModel<QueryOrderBResp> shanSongQueryOrderB(
			QueryShanSongOrderReq para) {
		if ( para.getBusinessId() == null
				|| para.getBusinessId() == 0
				|| (para.getStatus() != OrderStatus.New.value() && para.getStatus() != OrderStatus.Taking.value() && para.getStatus() != OrderStatus.Delivery
						.value() && para.getStatus()!=OrderStatus.Draft.value() && para.getStatus()!=OrderStatus.Complite.value() && para.getStatus()!=OrderStatus.Cancel.value())) {
			HttpResultModel<QueryOrderBResp> result=new HttpResultModel<QueryOrderBResp>();
			result.setStatus(HttpReturnRnums.ParaError.value());
			result.setMessage(HttpReturnRnums.ParaError.desc());
			return result;   
		}
		para.setDateInfo(null);
		para.setClienterId(null);
		return orderService.shanSongQueryOrderB(para);
	}
	
	/**
	 * 里程计算 推单  (处理订单)
	 * @author CaoHeYang 
	 * @date 20160105
	 * @param req
	 * @return
	 */
	public HttpResultModel<Boolean> shanSongPushOrder(ShanSongPushOrderReq req) {
		HttpResultModel<Boolean> res = new HttpResultModel<Boolean>();
		return res.setResult(orderService.shanSongPushOrder(req.getOrderId()));
	}
}
