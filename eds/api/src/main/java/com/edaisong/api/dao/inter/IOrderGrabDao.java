package com.edaisong.api.dao.inter;


import java.util.List;

import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.resp.MyOrderGrabCResp;
import com.edaisong.entity.resp.MyOrderGrabDetailResp;

public interface IOrderGrabDao {
    int deleteById(Long id);
    int insert(OrderGrab record);
    OrderGrab selectById(Long id);
    int insertSelective(OrderGrab record);
    List<FastOrderMapDetail> getMapDetailById(Long id);
    PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req);
    List<FastOrderExportModel> exportOrder(PagedFastOrderSearchReq req);
	List<MyOrderGrabCResp> getMyOrderGrabC(MyOrderGrabCReq myOrderGrabCReq);
	MyOrderGrabDetailResp getMyOrderGrabDetailC(OrderGrabDetailCReq orderGrabDetailCReq);
}