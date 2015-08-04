package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.domain.BusinessThirdRelationModel;

public interface IBusinessThirdRelationService {
    List<BusinessThirdRelationModel> getListByBusinessID(int businessID);
}
