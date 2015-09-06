package com.edaisong.api.dao.inter;


import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;

public interface IAccountDao {
	
	PagedResponse<Account> query(PagedAccountReq req);
    Account login(String username,String password);
	Account getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
}