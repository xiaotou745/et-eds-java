package com.edaisong.entity.resp;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.ResponseBase;

@XmlRootElement(name = "testServiceResp")  
public class TestServiceResp extends ResponseBase{
 public List<BusinessBalanceRecord> getResultList() {
		return resultList;
	}

	public void setResultList(List<BusinessBalanceRecord> resultList) {
		this.resultList = resultList;
	}

private	List<BusinessBalanceRecord> resultList;


}
