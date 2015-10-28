package com.edaisong.api.dao.inter;

import com.edaisong.entity.ApiVersionStatistic;

public interface IApiVersionStatisticDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ApiVersionStatistic record);

    int insertSelective(ApiVersionStatistic record);

    ApiVersionStatistic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApiVersionStatistic record);

    int updateByPrimaryKey(ApiVersionStatistic record);
}