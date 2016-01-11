package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.resp.OrderTipResp;
import com.edaisong.entity.resp.TaskDistributionConfigResp;

public interface IOrderTipService {
	public List<OrderTip> getList();
	
	public PagedResponse<OrderTip> query(PagedOrderTipReq req);
	
	HttpResultModel<Object>  add(OrderTip record);	
	
	HttpResultModel<OrderTipResp> modify(OrderTip record);	

    
}
