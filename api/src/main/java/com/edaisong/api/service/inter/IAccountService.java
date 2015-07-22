package com.edaisong.api.service.inter;

import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

public interface IAccountService {
	public AccountResp queryAccount(AccountReq req);
}
