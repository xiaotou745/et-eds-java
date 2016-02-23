package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IQuartzServiceDao;
import com.edaisong.toolsentity.QuartzServiceModel;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.PagedQuartzServiceReq;
import com.edaisong.toolsentity.req.QuartzUpdateReq;

@Repository
public class QuartzServiceDao extends DaoBase implements IQuartzServiceDao {

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	@Override
	public int updateStatus(QuartzUpdateReq req) {
		return getMasterSqlSessionUtil().update(
				"IQuartzServiceDao.updateStatus", req);
	}

	@Override
	public PagedResponse<QuartzServiceModel> pagedQuery(
			PagedQuartzServiceReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"IQuartzServiceDao.pagedQuery", req);
	}

	@Override
	public int insert(QuartzServiceModel record) {
		return getMasterSqlSessionUtil().insert("IQuartzServiceDao.insert",
				record);
	}

	@Override
	public int update(QuartzServiceModel record) {
		return getMasterSqlSessionUtil().update("IQuartzServiceDao.update",
				record);
	}

	@Override
	public List<QuartzServiceModel> queryStartList(int appSource) {
		return getReadOnlySqlSessionUtil().selectList(
				"IQuartzServiceDao.queryStartList", appSource);
	}

	@Override
	public int delete(long id) {
		return getMasterSqlSessionUtil().delete(
				"IQuartzServiceDao.delete", id);
	}
}
