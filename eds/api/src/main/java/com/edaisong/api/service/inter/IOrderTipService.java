package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
import com.edaisong.entity.req.PagedOrderTipReq;

public interface IOrderTipService {
	public List<OrderTip> getList();
	
	public PagedResponse<OrderTip> query(PagedOrderTipReq req);
	
    int  add(OrderTip record);
}
