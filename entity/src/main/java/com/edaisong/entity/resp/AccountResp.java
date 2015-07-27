package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.ResponseBase;

public class AccountResp  extends ResponseBase{
	public List<Account> getResultList() {
		return resultList;
	}

	public void setResultList(List<Account> resultList) {
		this.resultList = resultList;
	}

	private List<Account>  resultList;
}
