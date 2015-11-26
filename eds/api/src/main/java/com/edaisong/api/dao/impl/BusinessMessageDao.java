package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessMessageDao;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

/**
 * 商户消息数据访问层
 * @author pengyi
 * @date 20150818
 *
 */
@Repository
public class BusinessMessageDao extends DaoBase implements IBusinessMessageDao{
	/**
	 * 获得最新一条商户消息
	 * @author pengyi
	 * @date 20150818
	 */
	@Override
	public BusinessMessage getLatestMessage(int businessId) {
		return getReadOnlySqlSessionUtil()
				.selectOne("IBusinessMessageDao.getLatestMessage"
						, businessId);
	}

	/**
	 * 获得消息列表(分页)
	 * @author pengyi
	 * @date 20150821
	 */
	@Override
	public PagedResponse<BusinessMessage> getMessages(PagedBusinessMessageReq search) {
		PagedResponse<BusinessMessage> result = new PagedResponse<BusinessMessage>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"IBusinessMessageDao.getMessages", search);
		return result;
	}

}
