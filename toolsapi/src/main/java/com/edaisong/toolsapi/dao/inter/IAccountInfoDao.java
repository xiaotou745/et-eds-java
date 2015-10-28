package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.AccountInfo;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.UpdatePwdReq;
import com.edaisong.toolsentity.req.PagedAccountInfoReq;



public interface IAccountInfoDao {
	public  PagedResponse<AccountInfo>  queryAccount(PagedAccountInfoReq req);
	AccountInfo login(String username,String password);
	AccountInfo getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<AccountInfo> getByRoleID(int roleID);
	int insert(AccountInfo account);
	int update(AccountInfo account);
	int updatePwd(UpdatePwdReq req);
}