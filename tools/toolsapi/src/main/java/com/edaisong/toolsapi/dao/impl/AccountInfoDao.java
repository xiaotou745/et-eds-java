package com.edaisong.toolsapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository; 

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAccountInfoDao;
import com.edaisong.toolsentity.AccountInfo;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.UpdatePwdReq;
import com.edaisong.toolsentity.req.PagedAccountInfoReq;

@Repository
public class AccountInfoDao extends DaoBase implements IAccountInfoDao {
	// 查询所有管理后台用户列表
	@Override
	public PagedResponse<AccountInfo> queryAccount(PagedAccountInfoReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.query", req);
	}

	@Override
	public AccountInfo login(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.login", params);
	}

	@Override
	public AccountInfo getByID(int userID) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.getByID", userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("newRoleID", newRoleID);
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.updateRoleID", params);
	}

	@Override
	public List<AccountInfo> getByRoleID(int roleID) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.getByRoleID", roleID);
	}

	@Override
	public int insert(AccountInfo account) {
		return getMasterSqlSessionUtil().insert(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.insert", account);
	}

	@Override
	public int update(AccountInfo account) {
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.update", account);
	}

	@Override
	public int updatePwd(UpdatePwdReq req) {
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IAccountInfoDao.updatePwd", req);
	} 
}
