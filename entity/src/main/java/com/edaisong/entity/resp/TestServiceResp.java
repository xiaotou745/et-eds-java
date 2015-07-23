package com.edaisong.entity.resp;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.ResponseBase;

@XmlRootElement(name = "Response")  
public class TestServiceResp extends ResponseBase{
 private	List<BusinessBalanceRecord> ResultList;

public List<BusinessBalanceRecord> getResultList() {
	return ResultList;
}

public void setResultList(List<BusinessBalanceRecord> resultList) {
	ResultList = resultList;
}
}
