package com.edaisong.api.dal.dao.impl;


import org.springframework.stereotype.Repository;
import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.PagedBusinessReq;
@Repository
public class BusinessDao extends DaoBase implements IBusinessDao{

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		PagedResponse<BusinessModel> model = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessList", req);
		return model;
	}

}
