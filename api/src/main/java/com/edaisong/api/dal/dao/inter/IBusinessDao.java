package com.edaisong.api.dal.dao.inter;


import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessReq;

public interface IBusinessDao {
    ResponsePageList<BusinessModel> getBusinessList(BusinessReq req);
}