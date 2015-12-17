package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderRegionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

public interface IOrderRegionLogDao {
    int insert(OrderRegionLog record);
    List<OrderRegionLog> getList(Long businessId);
	PagedResponse<OrderRegionLog> getPagedList(PagedBusinessMessageReq req);
}