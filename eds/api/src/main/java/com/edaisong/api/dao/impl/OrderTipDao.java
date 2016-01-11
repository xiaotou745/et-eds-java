package com.edaisong.api.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;














import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IOrderTipDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AccountBillDayModel;
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillDetailModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.AccountBillCReq;
import com.edaisong.entity.req.AccountBillDetailReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.PagedAccountBillDayReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.req.PagedTransDetailReq;

@Repository
public class OrderTipDao extends DaoBase implements IOrderTipDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderTip record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(OrderTip record) {
		return getMasterSqlSessionUtil().insert(
				"IOrderTipDao.insertSelective", record);
	}

	@Override
	public OrderTip selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderTip record) {
		return getMasterSqlSessionUtil().update(
				"IOrderTipDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(OrderTip record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public List<OrderTip> getList()
	{	
		List<OrderTip> list = getReadOnlySqlSessionUtil().selectList(
				"IOrderTipDao.getList",
				"");
		return list;

	}
	@Override
    public PagedResponse<OrderTip> query(PagedOrderTipReq req)
    {
		PagedResponse<OrderTip> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IOrderTipDao.query",
						req);
		return model;
    }
	@Override
    public OrderTip selectByTip(int id, double amount)
	{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("amount", amount);
		
		return getMasterSqlSessionUtil().selectOne(
				"IOrderTipDao.selectByTip", paramMap);
	}


}
