package com.edaisong.api.service.inter;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

public interface IAccountService {
	public  ResponsePageList<Account>  queryAccount(AccountReq req);
}
