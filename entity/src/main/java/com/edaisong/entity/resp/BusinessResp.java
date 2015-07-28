package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.common.ResponseBase;

public class BusinessResp extends ResponseBase{
private List<Business> ResultList;

public List<Business> getResultList() {
	return ResultList;
}

public void setResultList(List<Business> resultList) {
	ResultList = resultList;
}
}
