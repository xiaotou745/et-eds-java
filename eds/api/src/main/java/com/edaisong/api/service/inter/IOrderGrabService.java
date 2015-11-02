package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel;
import com.edaisong.entity.req.PagedFastOrderSearchReq;

public interface IOrderGrabService {
    int deleteById(Long id);
    int insert(OrderGrab record);
    OrderGrab selectById(Long id);
    List<FastOrderMapDetail> getMapDetailById(Long id);
    PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req);
}
