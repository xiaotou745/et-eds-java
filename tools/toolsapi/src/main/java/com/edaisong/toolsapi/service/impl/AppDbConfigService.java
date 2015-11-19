package com.edaisong.toolsapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAppDbConfigDao;
import com.edaisong.toolsapi.service.inter.IAppDbConfigService;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.common.PagedRequestBase;
import com.edaisong.toolsentity.common.PagedResponse;
@Service
public class AppDbConfigService implements IAppDbConfigService{

	@Autowired
	private IAppDbConfigDao appDbConfigDao;
	@Override
	public int deleteById(Long id) {
return appDbConfigDao.deleteById(id);
	}

	@Override
	public int insert(AppDbConfig record) {
return appDbConfigDao.insert(record);
	}

	@Override
	public int updateById(AppDbConfig record) {
return appDbConfigDao.updateById(record);
	}

	@Override
	public PagedResponse<AppDbConfig> query(PagedRequestBase req) {
	return appDbConfigDao.query(req);
	}

}
