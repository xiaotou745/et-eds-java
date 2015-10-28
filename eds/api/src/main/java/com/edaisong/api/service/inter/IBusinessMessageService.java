package com.edaisong.api.service.inter;

import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

public interface IBusinessMessageService {
	BusinessMessage getLatestMessage(int businessId);
	
	/**
	 * 获得消息列表(分页)
	 * @author pengyi
	 * @date 20150821
	 */
	PagedResponse<BusinessMessage> getMessages(PagedBusinessMessageReq search);
}
