package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderTip;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.PagedTransDetailReq;

public interface IOrderTipDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderTip record);

    int insertSelective(OrderTip record);

    OrderTip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderTip record);

    int updateByPrimaryKey(OrderTip record);
    
	public List<OrderTip> getList();
		
}