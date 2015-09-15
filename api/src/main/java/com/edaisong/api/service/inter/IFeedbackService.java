package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Feedback;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.Group;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;

public interface IFeedbackService {
	 
	 PagedResponse<FeedbackModel> query(PagedFeedbackReq req);
	 
	 int  addByData(String data) ;
}
