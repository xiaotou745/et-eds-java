package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAppDbConfigDao;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.common.PagedRequestBase;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;
@Repository
public class AppDbConfigDao extends DaoBase implements IAppDbConfigDao{

	@Override
	public int deleteById(Long id) {
return getMasterSqlSessionUtil().delete("IAppDbConfigDao.deleteById", id);
	}

	@Override
	public int insert(AppDbConfig record) {
return getMasterSqlSessionUtil().insert("IAppDbConfigDao.insert", record);
	}

	@Override
	public int updateById(AppDbConfig record) {
return getMasterSqlSessionUtil().update("IAppDbConfigDao.updateById", record);
	}

	@Override
	public PagedResponse<AppDbConfig> query(PagedAppDbConfigReq req) {
return getReadOnlySqlSessionUtil().selectPageList("IAppDbConfigDao.query", req);
	}

	@Override
	public  List<AppDbConfig> queryList(PagedAppDbConfigReq req) {
return getReadOnlySqlSessionUtil().selectList("IAppDbConfigDao.queryList",req);
	}

}
