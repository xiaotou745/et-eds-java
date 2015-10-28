package com.edaisong.api.service.inter;

import com.edaisong.entity.AlipayBatch;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAlipayBatchListReq;

/**
 * 支付宝批次表
 * @author CaoHeYang
 * @date 20151020
 */
public interface IAlipayBatchService {
	/**
	 * 支付宝批次分页数据
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	PagedResponse<AlipayBatch>  getAlipayBatchPagedList(PagedAlipayBatchListReq req);
	/**
	*根据id获取 支付宝批次
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	AlipayBatch  getAlipayBatchById(Long id);
	
}
