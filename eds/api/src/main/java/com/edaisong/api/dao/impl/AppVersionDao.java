package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAppVersionDao;
import com.edaisong.entity.AppVersion;
import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.VersionCheckReq;




@Repository
public class AppVersionDao extends DaoBase implements IAppVersionDao {

	@Override
	public AppVersion getVersionCheck(VersionCheckReq req) {
		return getReadOnlySqlSessionUtil().selectOne(
				"IAppVersionDao.getVersionCheck", req);
	}

	@Override
	public PagedResponse<AppVersion> queryAppVersion(PagedRequestBase req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"IAppVersionDao.queryAppVersion", req);
	}

	@Override
	public AppVersion getByID(int id) {
		return getReadOnlySqlSessionUtil().selectOne("IAppVersionDao.getByID",
				id);
	}

	@Override
	public int insert(AppVersion record) {
		return getMasterSqlSessionUtil()
				.insert("IAppVersionDao.insert", record);
	}

	@Override
	public int update(AppVersion record) {
		return getMasterSqlSessionUtil()
				.update("IAppVersionDao.update", record);
	}

	@Override
	public int cancel(int id, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("userName", userName);
		return getMasterSqlSessionUtil()
				.update("IAppVersionDao.cancel", params);
	}

	@Override
	public int modify() {
		return getMasterSqlSessionUtil().update("IAppVersionDao.modify");
	}

}
