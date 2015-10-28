package com.edaisong.api.dao.inter;

import com.edaisong.entity.Statistic;

public interface IStatisticDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    Statistic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);
}