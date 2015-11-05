package com.edaisong.api.dao.inter;


import java.util.List;

import com.edaisong.entity.Group;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.domain.MyOrderGrabCModel;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;

public interface IOrderGrabDao {
	
	int insert(OrderGrab record);	
	int insertSelective(OrderGrab record);
    int deleteById(Long id);
    int updateByPrimaryKeySelective(OrderGrab record);
    OrderGrab selectByPrimaryKeyWrite(Integer id);
    
    FastOrderDetail selectById(Long id);
    List<FastOrderMapDetail> getMapDetailById(Long id);
    PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req);
    List<FastOrderExportModel> exportOrder(PagedFastOrderSearchReq req);
    List<MyOrderGrabCModel> getMyOrderGrabC(MyOrderGrabCReq myOrderGrabCReq);
	MyOrderGrabDetailCResp getMyOrderGrabDetailC(OrderGrabDetailCReq orderGrabDetailCReq);
	MyOrderGrabCResp getMyOrderGrabCTotalInfo(MyOrderGrabCReq myOrderGrabCReq);
	 
}