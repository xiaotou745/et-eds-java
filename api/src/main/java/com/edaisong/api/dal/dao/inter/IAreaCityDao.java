package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.AreaCity;

public interface IAreaCityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaCity record);

    int insertSelective(AreaCity record);

    AreaCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaCity record);

    int updateByPrimaryKey(AreaCity record);
}