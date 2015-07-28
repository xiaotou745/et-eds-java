package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusinessModel;

public class BusinessResp extends ResponseBase{
private List<BusinessModel> ResultList;

public List<BusinessModel> getResultList() {
	return ResultList;
}

public void setResultList(List<BusinessModel> resultList) {
	ResultList = resultList;
}
}
