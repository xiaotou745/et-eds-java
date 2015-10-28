package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.resp.BusinessLoginResp;

public interface IAccountService {
	public  PagedResponse<Account>  queryAccount(PagedAccountReq req);
	Account login(String username,String password);
	Account getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<Account> getByRoleID(int roleID);
}
