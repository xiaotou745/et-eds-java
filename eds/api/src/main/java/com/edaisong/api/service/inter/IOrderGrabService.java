package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.HadFinishOrderReq;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.resp.HadFinishOrderResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.req.OrderGrabCompleteReq;
import com.edaisong.entity.req.OrderGrabConfirmTakeReq;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.resp.OrderGrabResp;
public interface IOrderGrabService {
	int insert(OrderGrab record);
	int deleteById(Long id);   
    FastOrderDetail selectById(Long id);
    List<FastOrderMapDetail> getMapDetailById(Long id);
    PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req);
    List<FastOrderExportModel> exportOrder(PagedFastOrderSearchReq req);
/**
	 * 添加订单基础信息表
	 * 
	 * @param req
	 *            
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:15:24
	 * @return
	 */	
	int  add(OrderGrab record);
	
	/**
	 * 骑士抢单         
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:16:24
	 * @param req 
	 * @return
	 */	
	HttpResultModel<OrderGrabResp> GrabOrder(OrderGrabReq req);
	
	/**
	 * 骑士取货 
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:16:24
	 * @param req 
	 * @return
	 */	
	HttpResultModel<Integer> ConfirmTake(OrderGrabConfirmTakeReq req);
	
	/**
	 * 骑士完成订单
	 * @author 胡灵波
	 * @Date 2015年11月4日 11:17:46
	 * @param req 
	 * @return
	 */	
	HttpResultModel<Integer> Complete(OrderGrabCompleteReq req);
	
	
	MyOrderGrabCResp getMyOrderGrabC(MyOrderGrabCReq myOrderGrabCReq);
	MyOrderGrabDetailCResp getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq);

	HadFinishOrderResp getHadFinishOrderC(HadFinishOrderReq para); 
}