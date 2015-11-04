package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;

import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.resp.OrderGrabResp;
public interface IOrderGrabService {
    int deleteById(Long id);
    int insert(OrderGrab record);
    FastOrderDetail selectById(Long id);
    List<FastOrderMapDetail> getMapDetailById(Long id);
    PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req);
    List<FastOrderExportModel> exportOrder(PagedFastOrderSearchReq req);
/**
	 * ?????????
	 * 
	 * @param req
	 *            ????
	 * @author ???��
	 * @Date 2015??11??2?? 16:15:23
	 * @return
	 */	
	int  add(OrderGrab record);
	
	/**
	 * ����
	 * ????
	 * 
	 * @param req
	 *            ����
	 * @author ���鲨
	 * @Date 2015��11��2�� 16:15:23
	 * @param req 
	 * @author wangchao
	 * ��ȡ�ҵ�����

	 * @return
	 */	
	HttpResultModel<OrderGrabResp> GrabOrder(OrderGrabReq req);
	List<MyOrderGrabCResp> getMyOrderGrabC(MyOrderGrabCReq myOrderGrabCReq);
	MyOrderGrabDetailCResp getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq); 
}
