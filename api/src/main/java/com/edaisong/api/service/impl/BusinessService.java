package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private IBusinessDao iBusinessDao;

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		return iBusinessDao.getBusinessList(req);
	}

	/**
	 * 商家登录
	 * @param req
	 * @return
	 */
	public BusinessLoginResp login(BusinessLoginReq req){
		return null;
	}
	
}
