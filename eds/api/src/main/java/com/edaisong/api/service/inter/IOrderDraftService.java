package com.edaisong.api.service.inter;

import java.text.ParseException;
import java.util.List;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.Group;
import com.edaisong.entity.OrderDraft;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.OrderDraftGetReq;
import com.edaisong.entity.req.OrderDraftReq;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
import com.edaisong.entity.resp.OrderDraftResp;

public interface IOrderDraftService {
	 
	HttpResultModel<OrderDraftResp>  add(OrderDraftReq record)  ;
	
	HttpResultModel<OrderDraft> selectByPrimaryKey(Integer id) ;
}
