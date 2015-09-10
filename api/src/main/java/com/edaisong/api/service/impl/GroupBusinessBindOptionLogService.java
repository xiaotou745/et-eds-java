package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGroupBusinessBindOptionLogDao;
import com.edaisong.api.service.inter.IGroupBusinessBindOptionLogService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBindOptionLogModel;
import com.edaisong.entity.req.PagedBusinessBindLogReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 上午9:59:57
 * @version 1.0
 * @parameter
 * @since
 */
@Service
public class GroupBusinessBindOptionLogService implements IGroupBusinessBindOptionLogService{

	@Autowired
	private IGroupBusinessBindOptionLogDao groupBusinessBindOptionLogDao;
	@Override
	public PagedResponse<GroupBusinessBindOptionLogModel> getBusinessBindLogList(PagedBusinessBindLogReq req) {
		return groupBusinessBindOptionLogDao.getBusinessBindLogList(req);
	}
	
}
