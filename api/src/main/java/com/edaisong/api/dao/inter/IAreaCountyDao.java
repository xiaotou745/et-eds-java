package com.edaisong.api.dao.inter;

import com.edaisong.entity.AreaCounty;

public interface IAreaCountyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaCounty record);

    int insertSelective(AreaCounty record);

    AreaCounty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaCounty record);

    int updateByPrimaryKey(AreaCounty record);
}