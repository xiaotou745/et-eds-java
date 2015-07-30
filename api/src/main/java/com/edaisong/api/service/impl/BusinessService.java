package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.resp.BusinessResp;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private IBusinessDao iBusinessDao;

	@Override
	public ResponsePageList<BusinessModel> getBusinessList(BusinessReq req) {
		ResponsePageList<BusinessModel> resp = new ResponsePageList<BusinessModel>();
		if (req==null) {
			resp.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			resp.setMessage("请求的参数不能为null");
			return resp;
		}
		return iBusinessDao.getBusinessList(req);
	}

}
