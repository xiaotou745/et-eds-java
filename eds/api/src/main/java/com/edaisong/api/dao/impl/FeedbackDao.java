package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.dao.inter.IFeedbackDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.Feedback;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@Repository
public class FeedbackDao extends DaoBase implements IFeedbackDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Feedback record) {
		// TODO Auto-generated method stub	
		return getMasterSqlSessionUtil().insert(
				"IFeedbackDao.insert", record);
	}

	@Override
	public int insertSelective(Feedback record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Feedback selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Feedback record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Feedback record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<FeedbackModel> query(PagedFeedbackReq req) {

		PagedResponse<FeedbackModel> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IFeedbackDao.query",
						req);
		return model;
	}

}
