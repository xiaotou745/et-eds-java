package com.edaisong.api.service.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.ExportOrder;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.req.OptOrder;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.req.PagedBusTaskListReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.BusinessBalanceInfoResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderGrabResp;
import com.edaisong.entity.resp.OrderPushResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.resp.QueryOrderCResp;

public interface IOrderGrabService {
	/**
	 * 添加抢单表
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年11月2日 16:15:23
	 * @return
	 */	
	int  add(OrderGrab record);
	
	/**
	 * 抢单
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年11月2日 16:15:23
	 * @return
	 */	
	OrderGrabResp GrabOrder(OrderGrabReq req);
}
