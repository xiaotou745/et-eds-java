package com.edaisong.api.dal.dao.inter;
import java.util.List;


import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.domain.OpenCityModel;

public interface IPublicProvinceCityDao {
    int deleteByPrimaryKey(Integer code);

    int insert(PublicProvinceCity record);

    int insertSelective(PublicProvinceCity record);

    PublicProvinceCity selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(PublicProvinceCity record);

    int updateByPrimaryKey(PublicProvinceCity record);
    
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
    List<OpenCityModel> getOpenCityList(String  cityName);
}