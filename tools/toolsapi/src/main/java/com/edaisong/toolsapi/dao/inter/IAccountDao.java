package com.edaisong.toolsapi.dao.inter;


import java.util.List;

import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.PagedAccountReq;
import com.edaisong.toolsentity.req.UpdatePwdReq;

public interface IAccountDao {
	
	PagedResponse<Account> query(PagedAccountReq req);
    Account login(String username,String password);
	Account getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<Account> getByRoleID(int roleID);
	int updatePwd(UpdatePwdReq req);
}