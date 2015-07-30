package com.edaisong.api.dal.dao.impl;


import org.springframework.stereotype.Repository;
import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessReq;
@Repository
public class BusinessDao extends DaoBase implements IBusinessDao{

	@Override
	public ResponsePageList<BusinessModel> getBusinessList(BusinessReq req) {
		ResponsePageList<BusinessModel> model = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessList", req);
		return model;
	}

}
