package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao;


@Service
public class PublicProvinceCityService implements IPublicProvinceCityService
{

	@Autowired
	 private  IPublicProvinceCityDao publicProvinceCityDao;
	 
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
	@Override
	public  List<PublicProvinceCity> getOpenCityList(String  cityName) {
		return publicProvinceCityDao.getOpenCityList(cityName);
	};
}
