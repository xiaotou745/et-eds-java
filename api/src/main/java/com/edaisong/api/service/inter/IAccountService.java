package com.edaisong.api.service.inter;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.resp.BusinessLoginResp;

public interface IAccountService {
	public  PagedResponse<Account>  queryAccount(AccountReq req);
}
