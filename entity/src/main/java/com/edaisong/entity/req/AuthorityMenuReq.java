package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

public class AuthorityMenuReq extends RequestBase{
private String AccountId;

public String getAccountId() {
	return AccountId;
}

public void setAccountId(String accountId) {
	AccountId = accountId;
}
}
