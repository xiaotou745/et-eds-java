package com.edaisong.api.dao.inter;

import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

public interface IBusinessMessageDao {
	/**
	 * 消息列表
	 * 
	 * @author pengyi
	 * @date 20150821
	 * @param 商户Id
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	PagedResponse<BusinessMessage> getMessages(PagedBusinessMessageReq search);
    
    /**
     * 获得最新一条商户消息
     * @param businessId
     * @author pengyi
     * @date 20150818
     * @return
     */
    BusinessMessage getLatestMessage(int businessId);
}