package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.dao.inter.IFeedbackDao;
import com.edaisong.api.dao.inter.IOrderTipCostDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.Feedback;
import com.edaisong.entity.OrderTipCost;
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
public class OrderTipCostDao extends DaoBase implements IOrderTipCostDao {

	@Override
	public int insert(OrderTipCost record) {
		return 0;
	}

	@Override
	public int insertSelective(OrderTipCost record) {
		return getMasterSqlSessionUtil().insert(
				"IOrderTipCostDao.insertSelective", record);
	}

	

}
