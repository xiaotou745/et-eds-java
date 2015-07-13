package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.CrossShopStatisticLog;

public interface ICrossShopStatisticLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CrossShopStatisticLog record);

    int insertSelective(CrossShopStatisticLog record);

    CrossShopStatisticLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrossShopStatisticLog record);

    int updateByPrimaryKey(CrossShopStatisticLog record);
}