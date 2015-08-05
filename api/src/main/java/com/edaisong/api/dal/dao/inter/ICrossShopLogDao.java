package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.CrossShopLog;

public interface ICrossShopLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrossShopLog record);

    int insertSelective(CrossShopLog record);

    CrossShopLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrossShopLog record);

    int updateByPrimaryKey(CrossShopLog record);
}