package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.PublicProvinceCity;

public interface IPublicProvinceCityDao {
    int deleteByPrimaryKey(Integer code);

    int insert(PublicProvinceCity record);

    int insertSelective(PublicProvinceCity record);

    PublicProvinceCity selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(PublicProvinceCity record);

    int updateByPrimaryKey(PublicProvinceCity record);
}