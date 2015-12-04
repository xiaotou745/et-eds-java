package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAppVersionDao;
import com.edaisong.api.service.inter.IAppVersionService;
import com.edaisong.entity.AppVersion;
import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.VersionCheckReq;


@Service
public class AppVersionService implements IAppVersionService {
	@Autowired
	private IAppVersionDao appVersionDao;

	@Override
	public AppVersion getVersionCheck(VersionCheckReq req) {
		return appVersionDao.getVersionCheck(req);
	}

	@Override
	public PagedResponse<AppVersion> queryAppVersion(PagedRequestBase req) {
		return appVersionDao.queryAppVersion(req);
	}

	@Override
	public AppVersion getByID(int id) {
		return appVersionDao.getByID(id);
	}

	@Override
	public int insert(AppVersion record) {
		return appVersionDao.insert(record);
	}

	@Override
	public int update(AppVersion record) {
		return appVersionDao.update(record);
	}

	@Override
	public int cancel(int id, String userName) {
		return appVersionDao.cancel(id, userName);
	}

	@Override
	public int modify() {
		return appVersionDao.modify();
	}

}
