package com.edaisong.api.service.inter;

import com.edaisong.entity.AppVersion;
import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.VersionCheckReq;


public interface IAppVersionService {

	public AppVersion getVersionCheck(VersionCheckReq req);
	public  PagedResponse<AppVersion>  queryAppVersion(PagedRequestBase req);

	AppVersion getByID(int id);
	int insert(AppVersion record);
	int update(AppVersion record);
	int cancel(int id,String userName);
	int modify();
}
