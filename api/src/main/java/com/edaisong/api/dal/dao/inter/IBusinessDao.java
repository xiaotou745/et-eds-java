package com.edaisong.api.dal.dao.inter;


import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.PagedBusinessReq;

public interface IBusinessDao {
    PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
}