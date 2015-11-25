package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderDraft;

public interface IOrderDraftDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDraft record);

    int insertSelective(OrderDraft record);

    OrderDraft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDraft record);

    int updateByPrimaryKey(OrderDraft record);
}