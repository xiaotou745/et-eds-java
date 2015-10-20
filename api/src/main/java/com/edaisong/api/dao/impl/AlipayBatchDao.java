package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAlipayBatchDao;
import com.edaisong.entity.AlipayBatch;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.PagedAlipayBatchListReq;

/**
 * 支付宝批次表
 * @author CaoHeYang
 * @date 20151020
 */
@Repository
public class AlipayBatchDao  extends DaoBase  implements IAlipayBatchDao {
 	/**
 * 支付宝批次分页数据
 * @author CaoHeYang
 * @param req
 * @return
 */
	@Override
	public PagedResponse<AlipayBatch>  getAlipayBatchPagedList(PagedAlipayBatchListReq req){
		PagedResponse<AlipayBatch> result = new PagedResponse<AlipayBatch>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IAlipayBatchDao.getAlipayBatchPagedList", req);
		return result;
	}
}
