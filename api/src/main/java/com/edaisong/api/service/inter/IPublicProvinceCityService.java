package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.domain.OpenCityModel;


/**
 * 省市区通用  业务逻辑层  
 * @author CaoHeYang
 * 20150720
 */
public interface IPublicProvinceCityService {

	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 * @param cityName 城市名称
	 */
	 List<OpenCityModel> getOpenCityList(String  cityName);
}
