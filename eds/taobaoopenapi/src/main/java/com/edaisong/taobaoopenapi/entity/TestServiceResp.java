package com.edaisong.taobaoopenapi.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusinessDetailModel;

@XmlRootElement(name = "testServiceResp")  
public class TestServiceResp extends OpenResponseBase{
 public BusinessDetailModel getResultList() {
		return resultList;
	}

	public void setResultList(BusinessDetailModel resultList) {
		this.resultList = resultList;
	}

private	BusinessDetailModel resultList;


}
