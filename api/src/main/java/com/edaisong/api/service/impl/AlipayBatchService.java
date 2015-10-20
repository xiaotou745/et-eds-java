package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.impl.AlipayBatchDao;
import com.edaisong.api.dao.inter.IAlipayBatchDao;
import com.edaisong.api.service.inter.IAlipayBatchService;
import com.edaisong.entity.AlipayBatch;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAlipayBatchListReq;

/**
 * 支付宝批次表
 * @author CaoHeYang
 * @date 20151020
 */
@Service
public class AlipayBatchService extends DaoBase implements IAlipayBatchService {
	
	/**
	 * 支付宝批次 
	 * @author CaoHeYang
	 * @date 20151020
	 */
	@Autowired
	private IAlipayBatchDao alipayBatchDao;
	/**
	 * 支付宝批次分页数据
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public PagedResponse<AlipayBatch>  getAlipayBatchPagedList(PagedAlipayBatchListReq req){
		return alipayBatchDao.getAlipayBatchPagedList(req);
	}
}
