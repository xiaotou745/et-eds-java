package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.BusinessExpressRelation;

public interface IBusinessExpressRelationService {
	 List<BusinessExpressRelation> selectByBusinessID(Integer businessID);
}
